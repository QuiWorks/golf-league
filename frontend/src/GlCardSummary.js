import {css, html, LitElement} from "lit-element";
import "@vaadin/vaadin-text-field/vaadin-text-field.js";
import "@vaadin/vaadin-text-field/vaadin-number-field.js";
import "@vaadin/vaadin-date-picker/vaadin-date-picker.js";
import "@vaadin/vaadin-text-field/vaadin-text-area.js";
import "@vaadin/vaadin-checkbox/vaadin-checkbox.js";

export class GlCardSummary extends LitElement {

    static get styles() {
        return css`
        `;
    }

    static get properties() {
        return {
            nine: {type: String},
            flight: {type: Number},
            date: {type: Date}
        };
    }

    constructor() {
        super();
        this.flight = 0;
        this.date = new Date();
        this.nine = "";
    }

    render() {
        return html`
            <div class="card-container">
            </div>
        `;
    }

}

window.customElements.define("gl-card-summary", GlCardSummary)