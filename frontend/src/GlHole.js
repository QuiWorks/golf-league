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
          max-width: 75px;
        }
        .column {
          display: flex;
          flex-direction: column;
          margin: 4px 0 4px 4px;
          max-width: 64px;
          justify-content: space-between;
        }
        
        .no-label {
            align-items: end;
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
        .input::part(decrease-button) {
            transform: rotate(90deg);
        }
        .value {
            margin-left:2px;
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

    _scoreChange(e) {
        this.score = e.target.value;
        this.dispatchEvent(new CustomEvent("gl-hole-score-change", {
            detail: this.getHoleData(), bubbles: true, composed: true
        }));
        this.dispatchEvent(new CustomEvent("gl-hole-score-sync", {
            detail: {value:this.score}, bubbles: true, composed: true
        }));
    }

    firstUpdated(_changedProperties) {
        super.firstUpdated(_changedProperties);
        this.dispatchEvent(new CustomEvent("gl-hole-first-update", {
            detail: {hole:this.number}, bubbles: true, composed: true
        }));
        this.dispatchEvent(new CustomEvent("gl-hole-score-change", {
            detail: this.getHoleData(), bubbles: true, composed: true
        }));
        this.dispatchEvent(new CustomEvent("gl-hole-score-sync", {
            detail: {value:this.score}, bubbles: true, composed: true
        }));
    }

    showLabel()
    {
        this.label = true;
        this.style.maxWidth = "100px";
        this.shadowRoot.querySelector(".no-label").classList.remove("no-label");
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
        <div class="column no-label">
          <div class="row">
              ${this.firstInFlexRow().map(() => html`<label>hole:</label>`)}
              <span class="value">${this.number}</span>
          </div>
          <div class="row">
            ${this.firstInFlexRow().map(() => html`<label>yrd:</label>`)}
            <span class="value">${this.yardage}</span>
          </div>
          <div class="row">
            ${this.firstInFlexRow().map(() => html`<label>par:</label>`)}
            <span class="value">${this.par}</span>
          </div>
          <div class="row">
            ${this.firstInFlexRow().map(() => html`<label>hdcp:</label>`)}
            <span class="value">${this.handicap}</span>
          </div>
        </div>
        <vaadin-integer-field class="input" has-controls @change="${this._scoreChange}" min="1" max="10" placeholder="3" value="${this.score}"></vaadin-integer-field>
    </div>
    `;
    }
}

window.customElements.define("gl-hole", GlHole)
