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
        
        .card-info vaadin-text-field[name='nine'] {
            max-width: 96px;
        }

        .flight-info {
          width: 64px;
        }
        
        .comments {
            margin:0;
            width:90%;
            margin:5%;
        }
        .submit {
            width:80%;
            margin-right:10%;
            margin-left:10%;
        }
        
        .column {
            display:flex;
            flex-direction:column;
        }
        `;
    }

    static get properties() {
        return {
            week: {type: Number},
            nine: {type: String},
            flight: {type: Number},
            team: {type: Number},
            date: {type: Date},
            comment: {type: String},
            noComment: {type: Boolean}
        };
    }

    constructor() {
        super();
        this.week = 1;
        this.flight = 1;
        this.team = 1;
        this.date = new Date();
        this.nine = "";
        this.comment = "";
        this.golferScores = [];
        this.noComment = false;
        this.weeks = [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19];
        this.flights = [1,2,3,4];
        this.teams = [1,2,3,4,5,6,7,8,9,10,11,12];
    }

    connectedCallback() {
        super.connectedCallback();
        this.addEventListener("gl-golfer-score-change", this._onGolferScoreChange.bind(this));
    }

    firstUpdated(_changedProperties) {
        super.firstUpdated(_changedProperties);
        this.shadowRoot.querySelector("#weeks").items = this.weeks;
        this.shadowRoot.querySelector("#flights").items = this.flights;
        this.shadowRoot.querySelector("#teams").items = this.teams;
    }

    _onGolferScoreChange(e) {
        // See if golfer score already exists and update it
        // if it doesn't exist add it.
        let golferScore = this.golferScores.map(gs => gs.golfer)
            .find(golfer => JSON.stringify(golfer) === JSON.stringify(e.detail.golfer));
        if (typeof find !== "undefined") {
            golferScore = e.detail;
        } else {
            this.golferScores.push(e.detail);
        }
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


    _onWeekChanged(e)
    {
        if(this.goodEvent(e)){
            this.week = e.target.value;
            this.dispatchEvent(new CustomEvent("gl-card-week-changed", {
                detail: {value: this.week}, bubbles: true, composed: true
            }));
        }
    }

    _onFlightChanged(e)
    {
        if(this.goodEvent(e)){
            this.flight = e.target.value;
            this.dispatchEvent(new CustomEvent("gl-card-flight-changed", {
                detail: {value: this.flight}, bubbles: true, composed: true
            }));
        }
    }

    _onTeamChanged(e)
    {
        if(this.goodEvent(e)){
            this.team = e.target.value;
            this.dispatchEvent(new CustomEvent("gl-card-team-changed", {
                detail: {value: this.team}, bubbles: true, composed: true
            }));
        }
    }

    search() {
        // Dispatch an application event.
        this.dispatchEvent(new CustomEvent("gl-card-request", {
            detail: this._getCardData(), bubbles: true, composed: true
        }));
    }

    goodEvent(e) {
        return (e.target.value !== null) && (typeof e.target.value == "string");
    }

    _getCardData() {
        return {
            week: this.week,
            flight: this.flight,
            team: this.team,
            nine: this.nine,
            date: this.date,
            comment: this.comment,
            scores: this.golferScores
        };
    }

    submit() {
        // Dispatch an application event.
        this.dispatchEvent(new CustomEvent("gl-card-submission", {
            detail: this._getCardData(), bubbles: true, composed: true
        }));
    }

    render() {
        return html`
            <div class="card-container">
                <div class="card-info-container">
                    <div class="card-info">
                        <vaadin-combo-box id="weeks" name="week" label="week" value="${this.week}" @change="${this._onWeekChanged}"></vaadin-combo-box>
                    </div>
                    <div class="card-info">
                        <vaadin-combo-box id="flights" name="flight" label="flight" value="${this.flight}" @change="${this._onFlightChanged}"></vaadin-combo-box>
                    </div>
                    <div class="card-info">
                        <vaadin-combo-box id="teams" name="team" label="Team" value="${this.team}" @change="${this._onTeamChanged}"></vaadin-combo-box>
                    </div>
                    <div class="card-info search">
                        <vaadin-button @click="${this.search}">search</vaadin-button>
                    </div>
                </div>
                <slot></slot>
            </div>
            ${this.noComment ? html`
                <div>
                    <vaadin-button class="submit">submit</vaadin-button>
                </div>
            ` : html`
                <div class="column">
                    <div class="card-info">
                        <vaadin-text-area class="comments" label="Comments:" name="comment"></vaadin-text-area>
                    </div>
                    <div class="card-info">
                        <vaadin-button @click="${this.submit}" class="submit">Update</vaadin-button>
                    </div>
                </div>`}
        `;
    }

}

window.customElements.define("gl-card", GlCard)