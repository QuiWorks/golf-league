import {css, html, LitElement} from "lit-element";
import "@vaadin/vaadin-text-field/vaadin-text-field.js";
import "@vaadin/vaadin-text-field/vaadin-number-field.js";
import "@vaadin/vaadin-date-picker/vaadin-date-picker.js";
import "@vaadin/vaadin-text-field/vaadin-text-area.js";
import "@vaadin/vaadin-checkbox/vaadin-checkbox.js";

export class GlCard extends LitElement {

    static get styles() {
        return css`
        :host {
          font-size: 12pt;
        }

        .card-info-container {
          display:flex;
          flex-direction: row;
          flex-wrap:wrap;
          justify-content: start;
        }
        
        .card-container {
          display:flex;
          flex-direction: column;
          align-content:center;
        }

        .card-info {
          display:flex;
          margin: 5px;
          align-items: center;
        }

        .small-width{
          width: 130px;
        }

        vaadin-text-area {
          width:90%;
          margin:5%;
        }
        
        .card-info vaadin-text-field[name='nine'] {
            max-width: 96px;
        }

        vaadin-number-field {
          width: 48px;
        }
        `;
    }

    static get properties() {
        return {
            team: {type: Number},
            nine: {type: String},
            flight: {type: Number},
            handicap: {type: Number},
            date: {type: Date},
            name: {type: String},
            sub: {type: Boolean},
            comment: {type: String}
        };
    }

    constructor() {
        super();
        this.flight = 0;
        this.date = new Date();
        this.nine = "";
    }

    formattedDate() {
        try {
            return this.date.toISOString().split("T")[0];
        } catch (e) {
            return this.date;
        }
    }

    _updateDate() {
        this.date = new Date(`${this.shadowRoot.querySelector("vaadin-date-picker").value}T12:00:00Z`);
    }

    render() {
        return html`
    <div class="card-container">
      <div class="card-info-container">        
        <div class="card-info">
            <vaadin-number-field name="flight" label="flight" value="${this.flight}" ></vaadin-number-field>
        </div>
        <div class="card-info">
          <vaadin-date-picker class="small-width" label="date" name="date" @change="${this._updateDate}" value="${this.formattedDate()}" ></vaadin-date-picker>
        </div>
        <div class="card-info">
           <vaadin-text-field class="small-width" label="nine" name="nine" value="${this.nine}" ></vaadin-text-field>
        </div>
      </div>
      <slot></slot>  
    </div>
    `;
    }

}

window.customElements.define("gl-card", GlCard)