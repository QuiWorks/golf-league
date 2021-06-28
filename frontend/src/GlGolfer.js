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
          --lumo-disabled-text-color: var(--lumo-contrast-70pct);
        }
        
        .column {
            display:flex;
            flex-direction:column;
        }
        
        .row {
            display:flex;
            flex-direction:row;
            flex-wrap:wrap;
        }
        
        .golfer-container {
          display:flex;
          flex-direction: row;
          align-content:center;
          justify-content: start;
          flex-wrap:wrap;
        }

        .golfer-info {
          display:flex;
          margin: 2px;
          align-items: center;
        }
        
        .check-box {
            padding-top:14px;
            display:flex;
            flex-direction:column;
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
            golfer: {type: Number},
            team: {type: Number},
            handicap: {type: Number},
            name: {type: String},
            sub: {type: Boolean},
            inline: {type: Boolean},
            hideHdcp: {type: Boolean}
        };
    }

    constructor() {
        super();
        this.golfer = 0;
        this.team = 0;
        this.handicap = 0;
        this.name = "";
        this.sub = false;
        this.inline = false;
        this.hideHdcp = false;
    }

    connectedCallback() {
        super.connectedCallback();
        this.addEventListener("gl-round-score-change", this._onRoundScoreChange.bind(this));
    }


    firstUpdated(_changedProperties) {
        super.firstUpdated(_changedProperties);
        let parent = this.shadowRoot.querySelector("div");
        if (this.inline) {
            parent.classList.remove("column");
            parent.classList.add("row");
        } else {
            parent.classList.remove("row");
            parent.classList.add("column");
        }
    }

    _onRoundScoreChange(e) {
        this.dispatchEvent(new CustomEvent("gl-golfer-score-change", {
            detail: {golfer: this._getGolferData(), round: e.detail.round},
            bubbles: true, composed: true
        }));
    }

    _getGolferData() {
        return {
            golfer: this.golfer,
            team: this.team,
            name: this.name,
            handicap: this.handicap,
            sub: this.sub
        };
    }

    render() {
        return html`
            <div>
                <div class="golfer-container">
                    <div class="golfer-info">
                        <vaadin-number-field name="team" label="team #" value="${this.team}" disabled></vaadin-number-field>
                    </div>
                    <div class="golfer-info">
                        <vaadin-text-field name="name" label="name" value="${this.name}" disabled></vaadin-text-field>
                    </div>
                    ${this.hideHdcp ? html``:html`
                    <div class="golfer-info">
                        <vaadin-number-field name="handicap" label="hdcp"
                                             value="${this.handicap}" disabled></vaadin-number-field>
                    </div>`}
                </div>
                <div class="column">
                    <slot></slot>
                </div>
            </div>
        `;
    }

}

window.customElements.define("gl-golfer", GlGolfer)