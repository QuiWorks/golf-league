import {css, html, LitElement} from "lit-element";
import "@vaadin/vaadin-text-field/vaadin-integer-field.js";

export class GlScore extends LitElement {

    static get styles() {
        return css`
        :host {
            --lumo-disabled-text-color: var(--lumo-contrast-70pct);
        }
        .display-field::part(input-field) {
            background-color: var(--gl-score-background-color);
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
            number: {type: Number},
            par: {type: Number},
            playerHandicap: {type: Number},
            holeHandicap: {type: Number},
            score: {type: Number}
        };
    }

    constructor() {
        super();
        this.number = 0;
        this.par = 0;
        this.playerHandicap = 0;
        this.holeHandicap = 0;
        this.score = 0;
    }

    firstUpdated(_changedProperties) {
        super.firstUpdated(_changedProperties);
        this.setBackgroundColor(this.score, this.par);
    }

    render() {
        return html`
            <div class="score-container">
                <vaadin-number-field class="display-field" name="score" label="H${this.number}" value="${this.score}"
                                     disabled></vaadin-number-field>
            </div>
        `;
    }

    setBackgroundColor(score, par) {
        const displayField = this.shadowRoot.querySelector(".display-field");
        if (score === par - 2) {
            displayField.style.setProperty("--gl-score-background-color", "var(--lumo-error-color-50pct)");
        } else if (score === par - 1) {
            displayField.style.setProperty("--gl-score-background-color", "var(--lumo-error-color-10pct)");
        } else if (score === par) {
            displayField.style.setProperty("--gl-score-background-color", "var(--lumo-shade-10pct)");
        } else if (score === par + 1) {
            displayField.style.setProperty("--gl-score-background-color", "var(--lumo-shade-30pct)");
        } else {
            displayField.style.setProperty("--gl-score-background-color", "var(--lumo-shade-50pct)");
        }
    }
}

window.customElements.define("gl-score", GlScore)
