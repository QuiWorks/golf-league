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

    render() {
        return html`
            <div>
                69
            </div>
        `;
    }
}

window.customElements.define("gl-score", GlScore)
