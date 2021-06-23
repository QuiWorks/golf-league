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
            week: {type: Number, reflect: true},
            flight: {type: Number, reflect: true},
            timeSlot: {type: Number, reflect: true}
        };
    }

    constructor() {
        super();
        this.flight = 1;
        this.slott = 1;
        this.week = 1;
        this.weeks = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19];
        this.flights = [1,2,3,4];
        this.slotts = [1,2,3,4,5,6];
    }

    firstUpdated(_changedProperties) {
        super.firstUpdated(_changedProperties);
        this.shadowRoot.querySelector("#weeks").items = this.weeks;
        this.shadowRoot.querySelector("#flights").items = this.flights;
        this.shadowRoot.querySelector("#slotts").items = this.slotts;
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
            slott: this.slott
        };
    }

    _onWeekChanged(e)
    {
        if(this.goodEvent(e)){
            this.week = e.target.value;
            this.dispatchEvent(new CustomEvent("gl-report-week-changed", {
                detail: {value: this.week}, bubbles: true, composed: true
            }));
        }
    }

    _onFlightChanged(e)
    {
        if(this.goodEvent(e)){
            this.flight = e.target.value;
            this.dispatchEvent(new CustomEvent("gl-report-flight-changed", {
                detail: {value: this.flight}, bubbles: true, composed: true
            }));
        }
    }

    _onSlottChanged(e)
    {
        if(this.goodEvent(e)){
            this.slott = e.target.value;
            this.dispatchEvent(new CustomEvent("gl-report-slott-changed", {
                detail: {value: this.slott}, bubbles: true, composed: true
            }));
        }
    }

    goodEvent(e) {
        return (e.target.value !== null) && (typeof e.target.value == "string");
    }

    render() {
        return html`
            <div class="report-container">
                <div class="report-info-container">
                    <div class="report-info">
                        <vaadin-combo-box id="weeks" name="week" label="week" value="${this.week}" @change="${this._onWeekChanged}"></vaadin-combo-box>
                    </div>
                    <div class="report-info">
                        <vaadin-combo-box id="flights" name="flight" label="flight" value="${this.flight}" @change="${this._onFlightChanged}"></vaadin-combo-box>
                    </div>
                    <div class="report-info">
                        <vaadin-combo-box id="slotts" name="slott" label="slot" value="${this.slott}" @change="${this._onSlottChanged}"></vaadin-combo-box>
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