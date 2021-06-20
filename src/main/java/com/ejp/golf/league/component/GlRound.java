/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.ejp.golf.league.component;

import com.ejp.golf.league.component.GlRound.Model;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;


/**
 * Simple template example.
 */
@Tag("gl-round")
@JsModule("./src/GlRound.js")
@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GlRound extends PolymerTemplate<Model> {

    /**
     * Template model which defines the single "name" property.
     */
    public interface Model extends TemplateModel {
        void setComment(String comment);

        String getComment();

        void setHandicap(int handicap);

        int getHandicap();

        void setGrossScore(int score);

        int getGrossScore();

        void setNetScore(int score);

        int getNetScore();

        void setNetPoints(int score);

        int getNetPoints();

        void setMatchPoints(int score);

        int getMatchPoints();

        void setTeamNet(int score);

        int getTeamNet();
    }

    public GlRound() {
    }

    public String getComment() {
        return getModel().getComment();
    }

    public void setComment(String team) {
        getModel().setComment(team);
    }

    public int getHandicap() {
        return getModel().getHandicap();
    }

    public void setHandicap(int handicap) {
        getModel().setHandicap(handicap);
    }

    public int getGrossScore() {
        return getModel().getGrossScore();
    }

    public void setGrossScore(int score) {
        this.getModel().setGrossScore(score);
    }

    public int getNetScore() {
        return getModel().getNetScore();
    }

    public void setNetScore(int score) {
        this.getModel().setNetScore(score);
    }

    public int getNetPoints() {
        return getModel().getNetPoints();
    }

    public void setNetPoints(int points) {
        getModel().setNetPoints(points);
    }

    public int getMatchPoints() {
        return getModel().getMatchPoints();
    }

    public void setMatchPoints(int points) {
        getModel().setMatchPoints(points);
    }

    public int getTeamNet() {
        return getModel().getNetPoints();
    }

    public void setTeamNet(int points) {
        getModel().setTeamNet(points);
    }
}
