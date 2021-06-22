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

import com.ejp.golf.league.event.GlCardSubmission;
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
@Tag("gl-report")
@JsModule("./src/GlReport.js")
@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GlReport extends PolymerTemplate<GlReport.Model>
{

    /**
     * Template model which defines the single "name" property.
     */
    public interface Model extends TemplateModel
	{
        void setWeek(int week);
        int getWeek();
        void setFlight(int flight);
        int getFlight();
        void setSlot(int slot);
        int getSlot();
//        @Encode() todo encode this https://vaadin.com/docs/v10/flow/polymer-templates/tutorial-template-model-encoders
        //  or this: https://vaadin.com/docs/v14/flow/binding-data/tutorial-flow-components-binder-validation
//        void setDate(Date date);
//        Date getDate();
    }

    public GlReport() {
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

    @Synchronize(value = "gl-report-flight-changed", property = "flight")
    public int getFlight()
    {
        return getModel().getFlight();
    }

    public void setFlight(int flight)
    {
        getModel().setFlight(flight);
    }

    @Synchronize(value = "gl-report-slot-changed", property = "slot")
    public int getSlot()
    {
        return getModel().getFlight();
    }

    public void setSlot(int slot)
    {
        getModel().setFlight(slot);
    }

    //    public Date getDate()
//    {
//        return getModel().getDate();
//    }
//
//    public void setDate(Date date)
//    {
//        getModel().setDate(date);
//    }
}
