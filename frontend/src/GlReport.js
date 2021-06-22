import {css, html, LitElement} from "lit-element";
import "@vaadin/vaadin-text-field/vaadin-text-field.js";
import "@vaadin/vaadin-text-field/vaadin-number-field.js";
import "@vaadin/vaadin-date-picker/vaadin-date-picker.js";
import "@vaadin/vaadin-text-field/vaadin-text-area.js";
import "@vaadin/vaadin-checkbox/vaadin-checkbox.js";

export class GlReport extends LitElement {

    static get styles() {
        return css`
        :host {
          font-size: 12pt;
        }

        .report-info-container {
          display:flex;
          flex-direction: row;
          flex-wrap:wrap;
          justify-content: start;
        }
        
        .report-container {
          display:flex;
          flex-direction: column;
          align-content:center;
        }

        .report-info {
          display:flex;
          margin: 2px;
          align-items: center;
        }

        .small-width{
          width: 130px;
        }

        vaadin-text-area {
          width:90%;
          margin:5%;
        }
        
        .report-info vaadin-text-field[name='nine'] {
            max-width: 96px;
        }
        
        .search {
            margin-top:10px;
            margin-top: 35px;
        }

        .flight-info {
          width: 64px;
        }
        
        .column {
            display:flex;
            flex-direction:column;
        }
        `;
    }

    static get properties() {
        return {
            nine: {type: String},
            name: {type: String},
            flight: {type: Number},
            slot: {type: Number},
            date: {type: Date}
        };
    }

    constructor() {
        super();
        this.flight = 0;
        this.slot = 0;
        this.date = new Date();
        this.nine = "";
        this.name = "";
        this.golferScores = [];
    }

    connectedCallback() {
        super.connectedCallback();
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

    search() {
        // Dispatch an application event.
        this.dispatchEvent(new CustomEvent("gl-report-request", {
            detail: this._getRequestData(), bubbles: true, composed: true
        }));
    }

    _getRequestData() {
        return {
            date: this.date,
            nine: this.nine,
            flight: this.flight,
            slot: this.slot,
            name: this.name,
        };
    }

    render() {
        return html`
            <div class="report-container">
                <div class="report-info-container">
                    <div class="report-info">
                        <vaadin-number-field name="flight" label="flight" value="${this.flight}" class="flight-info"></vaadin-number-field>
                    </div>
                    <div class="report-info">
                        <vaadin-number-field name="slot" label="slot" value="${this.slot}"></vaadin-number-field>
                    </div>
                    <div class="report-info">
                        <vaadin-date-picker class="small-width" label="date" name="date" @change="${this._updateDate}"
                                            value="${this.formattedDate()}"></vaadin-date-picker>
                    </div>
                    <div class="report-info">
                        <vaadin-text-field class="small-width" label="nine" name="nine"
                                           value="${this.nine}"></vaadin-text-field>
                    </div>
                    <div class="report-info search">
                        <vaadin-button @click="${this.search()}">search</vaadin-button>
                    </div>
                </div>
                <slot></slot>
            </div>`;
    }

}

window.customElements.define("gl-report", GlReport)