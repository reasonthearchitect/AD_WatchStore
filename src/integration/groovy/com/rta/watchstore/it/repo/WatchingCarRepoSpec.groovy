package com.rta.watchstore.it.repo

import com.rta.watchstore.it.AbstractItTest
import com.rta.watchstore.repo.IWatchingCarRepo
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.redis.core.RedisTemplate

class WatchingCarRepoSpec extends AbstractItTest {

    @Autowired IWatchingCarRepo watchingCarRepo;

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    def "a test to see if we can add vins to the list"() {

        setup:
        this.watchingCarRepo.save("test", "A234Vin1");
        this.watchingCarRepo.save("test", "A234Vin2");

        when:
        def listOfVims = this.watchingCarRepo.findAllForPerson("test");

        then:
        listOfVims != null
        listOfVims.size() == 2

        cleanup:
        this.redisTemplate.delete("test");

    }

    @Test
    def "a test to see if we can remove vins from the list"() {

        setup:
        this.watchingCarRepo.save("test", "A234Vin1");
        this.watchingCarRepo.save("test", "A234Vin2");

        when:

        def listOfVimsBefore = this.watchingCarRepo.findAllForPerson("test");
        this.watchingCarRepo.delete("test", "A234Vin1")
        def listOfVimsAfter = this.watchingCarRepo.findAllForPerson("test");

        then:
        listOfVimsBefore.size() == 2
        listOfVimsAfter.size() == 1

        cleanup:
        this.redisTemplate.delete("test");
    }
}
