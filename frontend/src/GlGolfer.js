import {css, html, LitElement} from "lit-element";
import "@vaadin/vaadin-text-field/vaadin-text-field.js";
import "@vaadin/vaadin-text-field/vaadin-number-field.js";
import "@vaadin/vaadin-date-picker/vaadin-date-picker.js";
import "@vaadin/vaadin-text-field/vaadin-text-area.js";
import "@vaadin/vaadin-checkbox/vaadin-checkbox.js";

export class GlGolfer extends LitElement {

    static get styles() {
        return css`
        :host {
          font-size: 12pt;
        }
        
        .column {
            display:flex;
            flex-direction:column;
        }
        
        .golfer-container {
          display:flex;
          flex-direction: row;
          align-content:center;
          justify-content: start;
        }

        .golfer-info {
          display:flex;
          margin: 5px;
          align-items: center;
        }
        
        .padding-top-large {
            padding-top:37px;
        }

        .small-width{
          width: 130px;
        }
        
        .golfer-info vaadin-text-field[name='nine'] {
            max-width: 96px;
        }

        vaadin-number-field {
          width: 48px;
        }
        
        label {
            margin-right: 4px;
            display:block;
            font-size: var(--lumo-font-size-xs);
            color: var(--lumo-contrast-70pct);
        }
        `;
    }

    static get properties() {
        return {
            team: {type: Number},
            handicap: {type: Number},
            name: {type: String},
            sub: {type: Boolean},
        };
    }

    constructor() {
        super();
        this.team = 0;
        this.handicap = 0;
        this.name = "";
        this.sub = false;
    }

    render() {
        return html`
    <div class="golfer-container">
        <div class="golfer-info">
            <vaadin-number-field name="team" label="team #" value="${this.team}" ></vaadin-number-field>
        </div>
        <div class="golfer-info">
            <vaadin-text-field name="name" label="name" value="${this.name}" ></vaadin-text-field>
        </div>
        <div class="golfer-info">
            <vaadin-number-field name="handicap" label="hdcp" value="${this.handicap}" ></vaadin-number-field>
        </div>
        <div class="golfer-info padding-top-large">
            <label for="sub">Sub?</label>
            <vaadin-checkbox name="sub" value="${this.sub}" ></vaadin-checkbox>
        </div>
    </div>
    <div class="column">
        <slot></slot>   
    </div>
        `;
    }

}

window.customElements.define("gl-golfer", GlGolfer)