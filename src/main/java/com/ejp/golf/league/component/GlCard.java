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

import com.ejp.golf.league.component.GlCard.Model;
import com.ejp.golf.league.event.GlCardRequest;
import com.ejp.golf.league.event.GlCardSubmission;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Synchronize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.polymertemplate.PolymerTemplate;
import com.vaadin.flow.shared.Registration;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.templatemodel.TemplateModel;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;


/**
 * Simple template example.
 */
@Tag("gl-card")
@JsModule("./src/GlCard.js")
@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GlCard extends PolymerTemplate<Model>
{

    /**
     * Template model which defines the single "name" property.
     */
    public interface Model extends TemplateModel
	{
        void setMatch(int match);
        int getMatch();
        void setTeam(int team);
        int getTeam();
        void setNine(String nine);
        String getNine();
        void setComment(String nine);
        String getComment();
        void setNoComment(Boolean noComment);
        Boolean getNoComment();
    }

    public GlCard() {
    }

    public Registration addCardRequestListener(ComponentEventListener<GlCardRequest> listener)
    {
        return addListener(GlCardRequest.class, listener);
    }

    public Registration addCardSubmissionListener(ComponentEventListener<GlCardSubmission> listener)
    {
        return addListener(GlCardSubmission.class, listener);
    }

    public int getMatch()
    {
        return getModel().getMatch();
    }

    public void setMatch(int match)
    {
        getModel().setMatch(match);
    }

    public int getTeam()
    {
        return getModel().getTeam();
    }

    public void setTeam(int team)
    {
        getModel().setTeam(team);
    }

    public String getNine()
    {
        return getModel().getNine();
    }

    public void setNine(String nine)
    {
        getModel().setNine(nine);
    }

    public String getComment()
    {
        return getModel().getNine();
    }

    public void setComment(String nine)
    {
        getModel().setNine(nine);
    }

    private Boolean noComment;

    public Boolean getNoComment() {
        return getModel().getNoComment();
    }

    public void setNoComment(Boolean noComment) {
        getModel().setNoComment(noComment);
    }
}
