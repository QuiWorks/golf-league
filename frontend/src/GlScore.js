import {css, html, LitElement} from "lit-element";
import "@vaadin/vaadin-text-field/vaadin-integer-field.js";

export class GlScore extends LitElement {

    static get styles() {
        return css`
        `;
    }

    static get properties() {
        return {
            number: {type: Number},
            par: {type: Number},
            handicap: {type: Number},
            score: {type: Number}
        };
    }

    constructor() {
        super();
        this.number = 0;
        this.par = 0;
        this.handicap = 0;
        this.score = 0;
    }

    getHoleData() {
        return {
            score: this.score,
            hole: this.number,
            par: this.par,
            handicap: this.handicap
        }
    }

    render() {
        return html`
            <div>
                gl-score place holder
            </div>
        `;
    }
}

window.customElements.define("gl-score", GlScore)
