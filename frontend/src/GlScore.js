import {css, html, LitElement} from "lit-element";
import "@vaadin/vaadin-text-field/vaadin-number-field.js";

export class GlScore extends LitElement {

    static get styles() {
        return css`
        :host {
            --lumo-disabled-text-color: var(--lumo-contrast-70pct);
        }
        .display-field::part(input-field) {
            background-color: var(--gl-score-background-color);
            font-weight:bold;
        }
        .display-field {
            width: 40px;
        }
        .score-container {
            margin-left: 2px;
            margin-right:2px;
        }
        `;
    }

    static get properties() {
        return {
            number: {type: Number, reflect: true},
            par: {type: Number, reflect: true},
            playerHandicap: {type: Number, reflect: true},
            holeHandicap: {type: Number, reflect: true},
            score: {type: Number, reflect: true},
            win: {type: Boolean, reflect: true},
            net: {type: Boolean, reflect: true}
        };
    }

    constructor() {
        super();
        this.number = 0;
        this.par = 0;
        this.playerHandicap = 0;
        this.holeHandicap = 0;
        this.score = 0;
        this.win = false;
        this.net = false;
    }

    firstUpdated(_changedProperties) {
        super.firstUpdated(_changedProperties);
        this._setBackgroundColor(this.score, this.par);
    }

    render() {
        return html`
            <div class="score-container">
                <vaadin-number-field class="display-field" name="score" label="H${this.number}(${this.par})" value="${this.score}"
                                     disabled></vaadin-number-field>
            </div>
        `;
    }

    _setBackgroundColor(score, par) {
        const displayField = this.shadowRoot.querySelector(".display-field");
        const netScore = score - par;
        if(!this.net) {
            if(score === 1) {
                displayField.style.setProperty("--gl-score-background-color", "royalblue");
            }else if (netScore <= -2) {
                displayField.style.setProperty("--gl-score-background-color", "#FF0000");
            } else if (netScore === -1) {
                displayField.style.setProperty("--gl-score-background-color", "#FFA0CB");
            } else if (netScore === 0) {
                displayField.style.setProperty("--gl-score-background-color", "white");
            } else if (netScore === 1) {
                displayField.style.setProperty("--gl-score-background-color", "#FFFFA0");
            } else {
                displayField.style.setProperty("--gl-score-background-color", "#898989");
            }
        }else{
            if(this.win)
            {
                displayField.style.setProperty("--gl-score-background-color", "#FF0000");
            }else{
                displayField.style.setProperty("--gl-score-background-color", "white");
            }
        }
    }
}

window.customElements.define("gl-score", GlScore)
