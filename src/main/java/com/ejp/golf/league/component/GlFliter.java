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

import com.ejp.golf.league.event.GlRequestSubmission;
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
@Tag("gl-filter")
@JsModule("./src/GlFilter.js")
@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GlFliter extends PolymerTemplate<GlFliter.Model>
{

    /**
     * Template model which defines the single "name" property.
     */
    public interface Model extends TemplateModel
	{
        void setWeek(int number);
        int getWeek();
        void setFlight(int number);
        int getFlight();
        void setTeam(int number);
        int getTeam();
        void setWeeks(int number);
        int getWeeks();
        void setFlights(int number);
        int getFlights();
        void setTeams(int number);
        int getTeams();

    }

    public GlFliter() {
    }


    public Registration addRequestSubmissionListener(ComponentEventListener<GlRequestSubmission> listener)
    {
        return addListener(GlRequestSubmission.class, listener);
    }

    @Synchronize(value = "gl-report-week-changed", property = "week")
    public int getWeek()
    {
        return getModel().getWeek();
    }

    public void setWeek(int week)
    {
        getModel().setWeek(week);
    }

    public int getWeeks()
    {
        return getModel().getWeeks();
    }

    public void setWeeks(int weeks)
    {
        getModel().setWeeks(weeks);
    }

    @Synchronize(value = "gl-report-flight-changed", property = "flight")
    public int getFlight()
    {
        return getModel().getFlight();
    }

    public void setFlight(int flight)
    {
        getModel().setFlight(flight);
    }
    public int getFlights()
    {
        return getModel().getFlights();
    }

    public void setFlights(int flights)
    {
        getModel().setFlights(flights);
    }

    @Synchronize(value = "gl-report-team-changed", property = "team")
    public int getTeam()
    {
        return getModel().getTeam();
    }

    public void setTeam(int team)
    {
        getModel().setTeam(team);
    }
    public int getTeams()
    {
        return getModel().getTeams();
    }

    public void setTeams(int teams)
    {
        getModel().setTeams(teams);
    }
}
