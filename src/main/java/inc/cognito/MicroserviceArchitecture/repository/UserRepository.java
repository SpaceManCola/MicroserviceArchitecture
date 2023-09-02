package inc.cognito.MicroserviceArchitecture.repository;

import inc.cognito.MicroserviceArchitecture.model.repository.Client;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

@Repository
public class UserRepository {
    private static final Logger LOGGER = Logger.getLogger(UserRepository.class.getName());
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
    }

    public Client getUser(BigInteger id) {
        LOGGER.info("get user with id: " + id);
        return jdbcTemplate.query("select * from client where id = ?",
                        (rs, rowNum) -> new Client(rs.getBigDecimal("id"), rs.getString("name")),
                        id)
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public Client addUser(Client client) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement ps = con.prepareStatement("insert into client (id,name) select max(id)+1, ? from client",
                    new String[]{"id"});
            ps.setString(1, client.getName());
            return ps;

        }, keyHolder);
        client.setId(BigInteger.valueOf((Long) keyHolder.getKey()));
        return client;
    }

    @Transactional
    public Client updateUser(Client client) {
        jdbcTemplate.update("update client set name = ? where id = ?", client.getName(), client.getId());
        return client;
    }

    @Transactional
    public void deleteUser(BigInteger id) {
        jdbcTemplate.update("delete from client where id = ?", id);
    }
}
