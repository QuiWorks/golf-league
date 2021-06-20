import {css, html, LitElement} from "lit-element";
import "@vaadin/vaadin-text-field/vaadin-integer-field.js";

export class GlScore extends LitElement {

    static get styles() {
        return css`
        :host {
            --lumo-disabled-text-color: var(--lumo-contrast-70pct);
        }
        .display-field::part(input-field) {
            background-color: var(--lumo-error-color-10pct);
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

    render() {
        return html`
            <div class="score-container">
                <vaadin-number-field class="display-field" name="score" label="H10" value="4" disabled></vaadin-number-field>
            </div>
        `;
    }
}

window.customElements.define("gl-score", GlScore)
