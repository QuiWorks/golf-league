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

import com.ejp.golf.league.component.GlHole.Model;
import com.vaadin.flow.component.Synchronize;
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
@Tag("gl-hole")
@JsModule("./src/GlHole.js")
@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GlHole extends PolymerTemplate<Model>
{

    /**
     * Template model which defines the single "name" property.
     */
    public interface Model extends TemplateModel
	{
        void setNumber(int number);
        int getNumber();
        void setYardage(int yardage);
        void setPar(int par);
        int getPar();
        void setHandicap(int handicap);
        int getHandicap();
        void setScore(int score);
        int getScore();
    }

    public GlHole() {
    }

    public GlHole(int number, int par, int yardage, int handicap, int score)
    {
        getModel().setNumber(number);
        getModel().setPar(par);
        getModel().setYardage(yardage);
        getModel().setHandicap(handicap);
        getModel().setScore(score);
    }

    public int getNumber()
    {
        return getModel().getNumber();
    }

    public void setNumber(int number)
    {
        getModel().setNumber(number);
    }

    public void setYardage(int yardage)
    {
        getModel().setYardage(yardage);
    }

    public int getPar()
    {
        return getModel().getPar();
    }

    public void setPar(int par)
    {
        getModel().setPar(par);
    }

    public int getHandicap()
    {
        return getModel().getHandicap();
    }

    public void setHandicap(int handicap)
    {
        getModel().setHandicap(handicap);
    }

    @Synchronize(value = "gl-hole-score-sync", property = "score")
    public int getScore()
    {
        return getModel().getScore();
    }

    public void setScore(int score)
    {
        getModel().setScore(score);
    }
}
