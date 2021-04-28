import {css, html, LitElement} from "lit-element";
import "@vaadin/vaadin-text-field/vaadin-integer-field.js";

/**
 * 50px wide by 168 tall
 */
export class GlHole extends LitElement {

    static get styles() {
        return css`
        :host {
          font-size: 12pt;
        }
        .column {
          display: flex;
          flex-direction: column;
          margin: 4px;
          max-width: 64px;
          justify-content: space-between;
        }
        .row {
          display:flex;
          margin: 2px;
          flex-direction:row;
          justify-content: space-between;
        }
        .input {
            transform: rotate(-90deg);
        }
        .input::part(value) {
            transform: rotate(90deg);
        }
        `;
    }

    static get properties() {
        return {
            number: {type: Number},
            yardage: {type: Number},
            par: {type: Number},
            handicap: {type: Number},
            score: {type: Number},
            label: {type: Boolean}
        };
    }

    constructor() {
        super();
        this.number = 0;
        this.yardage = 888;
        this.par = 0;
        this.handicap = 0;
        this.score = 0;
        this.label = false;
    }

    _scoreChange() {
        this.dispatchEvent(new CustomEvent("gl-hole-score-change", {
            detail: this.getHoleData(), bubbles: true, composed: true
        }));
    }

    firstUpdated(_changedProperties) {
        super.firstUpdated(_changedProperties);
        this.dispatchEvent(new CustomEvent("gl-hole-first-update", {
            detail: {hole:this.number}, bubbles: true, composed: true
        }));
    }

    showLabel()
    {
        this.label = true;
    }

    getHoleData()
    {
        return {
            score: this.score,
            hole: this.number,
            par: this.par,
            yardage: this.yardage,
            handicap: this.handicap
        }
    }

    firstInFlexRow() {
        return this.label ? [true] : [];
    }

    render() {
        return html`
    <div class="row">
        <div class="column">
          <div class="row">
              ${this.label? html`<label>Hole:</label><span>${this.number}</span>`:html`<span>${this.number}</span>`}
          </div>
          <div class="row">
            ${this.firstInFlexRow().map(() => html`<label>yrd:</label>`)}
            <span>${this.yardage}</span>
          </div>
          <div class="row">
            ${this.firstInFlexRow().map(() => html`<label>par:</label>`)}
            <span>${this.par}</span>
          </div>
          <div class="row">
            ${this.firstInFlexRow().map(() => html`<label>hdcp:</label>`)}
            <span>${this.handicap}</span>
          </div>
        </div>
        <vaadin-integer-field class="input" has-controls @change="${this._scoreChange}" min="1" max="10" placeholder="3" value="${this.score}"></vaadin-integer-field>
    </div>
    `;
    }
}

window.customElements.define("gl-hole", GlHole)
