package dao;

import connection.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa generica pentru a evita duplicarea codului care se ocupa
 * de legatura cu baza de date, statementurile sql fiind asemanatoare
 * pentru toate clasele / tabelele
 * @param <T> Purchase, Customer, Product. Implementate
 */
public class AbstractDAO<T> {
    /**
     * Preluarea clasei care acceseaza datele
     */
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());
    /**
     * tipul clasei care acceseaza datele
     */
    private final Class<T> type;

    /**
     * initializare tipul clasei
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * creaza un querry de selectare a elementelor
     * @param field numele coloanei care intra in conditie sau *
     *              daca se doreste selectarea intreguului tabel
     * @return statementul de select
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        if (!field.equals("*")) {
            sb.append(" WHERE ").append(field).append(" =?");
        }
        return sb.toString();
    }

    /**
     * creeaza un querry de insert  a elementelor in tabel
     * @param object obiectul pentru care se realizeaza insert
     *               se preiau campurile sale (cu reflection)
     * @return statementul de insert
     */
    private String createInsertQuery(Object object) {
        StringBuilder sb_fields = new StringBuilder();
        StringBuilder sb_values = new StringBuilder();
        sb_fields.append("INSERT INTO ");
        sb_fields.append(type.getSimpleName());
        sb_fields.append("("); //urmeaza table fields (fara id)

        sb_values.append("VALUES (");
        Field[] fields = object.getClass().getDeclaredFields();
        for (int i = 1; i < fields.length; i++) {
            fields[i].setAccessible(true); // Allow access to private fields
            String columnName = fields[i].getName();
            if (i > 1) {
                sb_fields.append(", ");
                sb_values.append(", ");
            }
            sb_fields.append(columnName);
            try {
                sb_values.append("'").append(fields[i].get(object)).append("'");
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }
        sb_fields.append(")");
        sb_values.append(")");

        return sb_fields.toString() + sb_values;
    }
    /**
     * creeaza un querry de update a unui element in tabel
     * @param object obiectul pentru care se realizeaza update,
     *               initializat cu valorile de inserat, considerand
     *               parametrul id neschimbat
     *               se preiau campurile sale (cu reflection)
     * @return statementul de update
     */
    private String createUpdateQuery(Object object) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        Field[] fields = object.getClass().getDeclaredFields();
        boolean firstField = true;
        for (Field field : fields) {
            field.setAccessible(true); // Allow access to private fields
            String columnName = field.getName();
            if (!columnName.equals(type.getSimpleName().toLowerCase() + "Id")) { // Skip the id field
                if (!firstField) {
                    sb.append(", ");
                }
                sb.append(columnName);
                sb.append(" = ");
                try {
                    sb.append("'").append(field.get(object)).append("'");
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                firstField = false;
            }
        }

        sb.append(" WHERE ").append(type.getSimpleName().toLowerCase()).append("Id = ");
        try {
            Field idField = object.getClass().getDeclaredField(type.getSimpleName().toLowerCase() + "Id");
            idField.setAccessible(true);
            sb.append(idField.get(object));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
    /**
     * creeaza un querry de delete a unui element in tabel
     * @param object obiectul pentru care se realizeaza delete
     *               se preiau campurile sale (cu reflection)
     * @return statementul de insert
     */
    private String createDeleteQuery(Object object) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ");
        sb.append(type.getSimpleName());

        sb.append(" WHERE " + type.getSimpleName().toLowerCase() + "Id = ");
        try {
            Field idField = object.getClass().getDeclaredField(type.getSimpleName().toLowerCase() + "Id");
            idField.setAccessible(true);
            sb.append(idField.get(object));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return sb.toString();
    }
    /**
     * utilizeaza querry ul de select pentru a cauta in baza de date
     * obiectul cu id-ul dat
     * @param id id-ul obiectului de cautat
     * @return primul obiect gasit in tabel sau null
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createSelectQuery(type.getSimpleName().toLowerCase() + "Id" );
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     * creeaza obiecte pe baza unui resultSet
     * @param resultSet rezultatul executiei unui querry
     * @return o lista de obiecte pe baza resultSet-ului
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T)ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException
                 | InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }
    /**
     * preia continutul unui tabel si il returneaza sub forma unei liste
     * @return o lista de obiecte
     */
    public List<T> getTableContent() {
        String query = createSelectQuery("*");
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:getTableContent " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }
    /**
     * utilizeaza querry ul de insert pentru a insera in baza de date
     * @param object obiectul de inserat
     * @param <T> tipul de obiect de inserat
     * @return id-ul obiectului inserat sau 0 daca nu a reusit insertul
     */
    public <T> int insert(T object) {
        int insertedId = 0; //checks if insert was successful
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = createInsertQuery(object);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            System.out.println(statement);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error executing insert: " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return insertedId;
    }
    /**
     * utilizeaza querry ul de update pentru a modifica un obiect in baza de date
     * @param object obiectul de inserat
     * @param <T> tipul de obiect de inserat
     * @return numarul de coloane afectate in executie
     */
    public <T> int update(T object) {
        int rowsAffected = 0; // checks if update was successful
        Connection connection = null;
        PreparedStatement statement = null;
        String insertString = createUpdateQuery(object);
        System.out.println(insertString);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(insertString);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error executing update: " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return rowsAffected;
    }

    /**
     * utilizeaza querry ul de delete pentru a sterge un element in baza de date
     * @param object obiectul de sters
     * @return numarul de coloane afectate in executie
     */

    public int delete(T object) {
        int rowsAffected = 0; // checks if delete was successful
        Connection connection = null;
        PreparedStatement statement = null;
        String deleteString = createDeleteQuery(object);
        System.out.println(deleteString);
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(deleteString);
            rowsAffected = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Error executing delete: " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return rowsAffected;
    }
}
