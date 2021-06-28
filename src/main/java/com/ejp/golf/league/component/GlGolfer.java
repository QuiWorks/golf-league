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

import com.ejp.golf.league.component.GlGolfer.Model;
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
@Tag("gl-golfer")
@JsModule("./src/GlGolfer.js")
@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GlGolfer extends PolymerTemplate<Model>
{
    public interface Model extends TemplateModel
	{
        void setGolfer(int golfer);
        int getGolfer();
        void setTeam(int team);
        int getTeam();
        void setHandicap(int handicap);
        int getHandicap();
        void setName(String name);
        String getName();
        void setSub(boolean sub);
        boolean isSub();
        void setInline(boolean inline);
        boolean isInline();
        void setHideHdcp(boolean hide);
        boolean isHideHdcp();
    }

    public GlGolfer() {
    }

    public int getGolfer()
    {
        return getModel().getGolfer();
    }

    public void setGolfer(int golfer)
    {
        getModel().setGolfer(golfer);
    }

    public int getTeam()
    {
        return getModel().getTeam();
    }

    public void setTeam(int team)
    {
        getModel().setTeam(team);
    }

    public int getHandicap()
    {
        return getModel().getHandicap();
    }

    public void setHandicap(int handicap)
    {
        getModel().setHandicap(handicap);
    }

    public String getName()
    {
        return getModel().getName();
    }

    public void setName(String name)
    {
        getModel().setName(name);
    }

    public boolean isSub()
    {
        return getModel().isSub();
    }

    public void setSub(boolean sub)
    {
        getModel().setSub(sub);
    }

    public boolean isInline()
    {
        return getModel().isInline();
    }

    public void setInline(boolean inline)
    {
        getModel().setInline(inline);
    }

    public boolean isHideHdcp() { return getModel().isHideHdcp();}

    public void setHideHdcp(boolean hide){ getModel().setHideHdcp(hide);}
}
