import {css, html, LitElement} from "lit-element";
import "@vaadin/vaadin-checkbox/vaadin-checkbox.js";

export class GlFilter extends LitElement {

    static get styles() {
        return css`
        .filter-container {
          display:flex;
          flex-direction: row;
          flex-wrap:wrap;
          align-content:center;
        }

        .filter-input {
          display:flex;
          margin: 2px;
          align-items: center;
        }
                
        .search {
            margin-top:10px;
            margin-top: 35px;
        }
        `;
    }

    static get properties() {
        return {
            week: {type: Number, reflect: true},
            flight: {type: Number, reflect: true},
            team: {type: Number, reflect: true},
            weeks: {type: Number},
            flights: {type: Number},
            teams: {type: Number},
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

    _onTeamChanged(e)
    {
        if(this.goodEvent(e)){
            this.team = e.target.value;
            this.dispatchEvent(new CustomEvent("gl-report-team-changed", {
                detail: {value: this.team}, bubbles: true, composed: true
            }));
        }
    }

    constructor() {
        super();
        this.week = 1;
        this.flight = 1;
        this.team = 1;
        this.weeks = 1;
        this.flights = 1;
        this.teams = 1;
    }

    _arrayOfSize(integer)
    {
        const arr = [];
        for (let i = 1; i <= integer; i++) {
            arr.push(i);
        }
        return arr;
    }

    firstUpdated(_changedProperties) {
        super.firstUpdated(_changedProperties);
        this.shadowRoot.querySelector("#weeks").items = this._arrayOfSize(this.weeks);
        this.shadowRoot.querySelector("#flights").items = this._arrayOfSize(this.flights);
        this.shadowRoot.querySelector("#teams").items = this._arrayOfSize(this.teams);
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
            team: this.team
        };
    }

    render() {
        return html`
            <div class="filter-container">
                <div class="filter-input">
                    <vaadin-combo-box id="weeks" name="week" label="week" value="${this.week}" @change="${this._onWeekChanged}" items="${JSON.stringify(this._arrayOfSize(this.weeks))}"></vaadin-combo-box>
                </div>
                <div class="filter-input">
                    <vaadin-combo-box id="flights" name="flight" label="flight" value="${this.flight}" @change="${this._onFlightChanged}"></vaadin-combo-box>
                </div>
                <div class="filter-input">
                    <vaadin-combo-box id="teams" name="team" label="team" value="${this.team}" @change="${this._onTeamChanged}"></vaadin-combo-box>
                </div>
                <div class="filter-input search">
                    <vaadin-button @click="${this.search}">search</vaadin-button>
                </div>
            </div>
        `;
    }
}

window.customElements.define("gl-filter", GlFilter)
