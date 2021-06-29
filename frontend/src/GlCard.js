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

        vaadin-text-area {
          width:90%;
          margin:5%;
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
            match: {type: Number},
            nine: {type: String},
            team: {type: Number},
            date: {type: Date},
            comment: {type: String},
            noComment: {type: Boolean}
        };
    }

    constructor() {
        super();
        this.match = 0;
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
        this.addEventListener("gl-filter-submission", this._onFilterSubmission.bind(this));
    }

    firstUpdated(_changedProperties) {
        super.firstUpdated(_changedProperties);
        this.querySelectorAll("gl-golfer").forEach(golfer => this.golferScores.push([golfer.golfer,0,0,0,0,0,0,0,0,0]))
    }

    submit() {
        // Dispatch an application event.
        this.dispatchEvent(new CustomEvent("gl-card-submission", {
            detail: this._getCardData(), bubbles: true, composed: true
        }));
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
    _onFilterSubmission(e)
    {
        e.preventDefault();
        this.dispatchEvent(new CustomEvent("gl-card-request", {
            detail: e.detail, bubbles: true, composed: true
        }));
    }
    _getCardData() {
        return {
            match: this.match,
            team: this.team,
            nine: this.nine,
            date: this.date,
            comment: this.comment,
            scores: JSON.stringify(this.golferScores)
        };
    }

    render() {
        return html`
            <div class="card-container">
                <slot name="filter"></slot>
                <slot name="card"></slot>
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