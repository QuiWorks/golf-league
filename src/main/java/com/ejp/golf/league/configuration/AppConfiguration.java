package com.ejp.golf.league.configuration;

import com.ejp.golf.league.domain.League;
import com.ejp.golf.league.repository.EventMatchRepository;
import com.ejp.golf.league.repository.GolferHandicapRepository;
import com.ejp.golf.league.repository.HoleRepository;
import com.ejp.golf.league.repository.RoundRepository;
import com.ejp.golf.league.service.ScoreCardService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Configuration
public class AppConfiguration {

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("golf_league");
    }

    @Bean
    public RoundRepository roundRepository() {
        return new RoundRepository();
    }

    @Bean
    public EventMatchRepository eventMatchRepository() {
        return new EventMatchRepository();
    }

    @Bean
    public HoleRepository holeRepository() {
        return new HoleRepository();
    }

    @Bean
    public GolferHandicapRepository golferHandicapRepository() {
        return new GolferHandicapRepository();
    }

//    TODO REMOVE HARD CODING
    @Bean
    public League league()
    {
        return new League().build(l -> l
                .set(l::id, 1)
                .set(l::name, "Territory Wednesday Mens League"));
    }
}
