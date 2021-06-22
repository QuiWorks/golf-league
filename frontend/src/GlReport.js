import {css, html, LitElement} from "lit-element";
import "@vaadin/vaadin-text-field/vaadin-text-field.js";
import "@vaadin/vaadin-text-field/vaadin-number-field.js";
import "@vaadin/vaadin-date-picker/vaadin-date-picker.js";
import "@vaadin/vaadin-text-field/vaadin-text-area.js";
import "@vaadin/vaadin-combo-box/vaadin-combo-box.js";
import "@vaadin/vaadin-checkbox/vaadin-checkbox.js";

export class GlReport extends LitElement {

    static get styles() {
        return css`
        :host {
          font-size: 12pt;
          margin-top:25px;
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
            flight: {type: Number, reflect: true},
            slot: {type: Number, reflect: true},
            week: {type: Number, reflect: true}
        };
    }

    constructor() {
        super();
        this.flight = 0;
        this.slot = 0;
        this.week = 0;
        this.weeks = [1,2,3,4];
        this.flights = [1,2,3,4];
        this.slots = [1,2,3,4,5,6];
    }

    firstUpdated(_changedProperties) {
        super.firstUpdated(_changedProperties);
        this.shadowRoot.querySelector("#weeks").items = this.weeks;
        this.shadowRoot.querySelector("#flights").items = this.flights;
        this.shadowRoot.querySelector("#slots").items = this.slots;
    }

    search() {
        // Dispatch an application event.
        this.dispatchEvent(new CustomEvent("gl-request-submission", {
            detail: this._getRequestData(), bubbles: true, composed: true
        }));
    }

    _getRequestData() {
        return {
            week: this.week,
            flight: this.flight,
            slot: this.slot
        };
    }

    _onWeekChanged(e)
    {
        this.week = e.target.value;
        this.dispatchEvent(new CustomEvent("gl-report-week-changed", {
            detail: {value: this.week}, bubbles: true, composed: true
        }));
    }

    _onFlightChanged(e)
    {
        this.flight = e.target.value;
        this.dispatchEvent(new CustomEvent("gl-report-flight-changed", {
            detail: {value: this.flight}, bubbles: true, composed: true
        }));
    }

    _onSlotChanged(e)
    {
        this.slot = e.target.value;
        this.dispatchEvent(new CustomEvent("gl-report-slot-changed", {
            detail: {value: this.slot}, bubbles: true, composed: true
        }));
    }



    render() {
        return html`
            <div class="report-container">
                <div class="report-info-container">
                    <div class="report-info">
                        <vaadin-combo-box id="weeks" name="week" label="week" @change="${this._onWeekChanged}"></vaadin-combo-box>
                    </div>
                    <div class="report-info">
                        <vaadin-combo-box id="flights" name="flight" label="flight" @change="${this._onFlightChanged}"></vaadin-combo-box>
                    </div>
                    <div class="report-info">
                        <vaadin-combo-box id="slots" name="slot" label="slot" @change="${this._onSlotChanged}"></vaadin-combo-box>
                    </div>
                    <div class="report-info search">
                        <vaadin-button @click="${this.search}">search</vaadin-button>
                    </div>
                </div>
                <slot></slot>
            </div>`;
    }

}

window.customElements.define("gl-report", GlReport)