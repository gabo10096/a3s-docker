package mx.com.example.services.service;

import mx.com.example.model.UserDO;
import mx.com.example.services.BaseTest;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class UsersServiceTest extends BaseTest {

    @Test
    public void shouldGetOneUser(){

        Long id =1l;
        int age =25;
        String name="Gabriel";
        String lastname="Aguilar";

        UserDO testUserDO = new UserDO(name, lastname,age);
        entityManager.persist(testUserDO);

        List<UserDO> result = usersService.getUsers(0,20, "name", "desc");

        Assert.assertNotNull(result);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(java.util.Optional.of(id).get(), result.get(0).getId());
        Assert.assertEquals(25, result.get(0).getAge());
        Assert.assertEquals("Gabriel", result.get(0).getName());
        Assert.assertEquals("Aguilar", result.get(0).getLastName());
    }

    @Test
    public void shouldUpdateOneUser(){

        int age =26;
        String name="Gabo";
        String lastname="Gonzalez";

        UserDO testUserDO = new UserDO(name, lastname,age);
        UserDO resultTestUserDO = entityManager.persist(testUserDO);

        int age2 =25;
        String name2="Gabriel";
        String lastname2="Aguilar";

        UserDO testUserDO2 = new UserDO(name2, lastname2,age2);
        testUserDO2.setId(resultTestUserDO.getId());
        usersService.saveUser(testUserDO2);

        UserDO result = entityManager.find(UserDO.class, resultTestUserDO.getId());

        Assert.assertNotNull(result);
        Assert.assertEquals(25, result.getAge());
        Assert.assertEquals("Gabriel", result.getName());
        Assert.assertEquals("Aguilar", result.getLastName());

    }
}
