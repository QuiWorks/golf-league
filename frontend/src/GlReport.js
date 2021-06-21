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
        this.golferScores = [];
    }

    connectedCallback() {
        super.connectedCallback();
        this.addEventListener("gl-golfer-score-change", this._onGolferScoreChange.bind(this));
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

    _getCardData() {
        return {
            flight: this.flight,
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
                        <vaadin-number-field name="flight" label="flight" value="${this.flight}" class="flight-info"></vaadin-number-field>
                    </div>
                    <div class="card-info">
                        <vaadin-number-field name="slot" label="slot" value="${this.slot}"></vaadin-number-field>
                    </div>
                    <div class="card-info">
                        <vaadin-date-picker class="small-width" label="date" name="date" @change="${this._updateDate}"
                                            value="${this.formattedDate()}"></vaadin-date-picker>
                    </div>
                    <div class="card-info">
                        <vaadin-text-field class="small-width" label="nine" name="nine"
                                           value="${this.nine}"></vaadin-text-field>
                    </div>
                    <div class="card-info search">
                        <vaadin-button>search</vaadin-button>
                    </div>
                </div>
                <slot></slot>
            </div>`;
    }

}

window.customElements.define("gl-report", GlReport)