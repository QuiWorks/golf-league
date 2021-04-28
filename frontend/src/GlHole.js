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
          min-width: 48px;
          max-width: 96px;
        }
        .row {
          display:flex;
          flex-direction:row;
          justify-content: space-between;
          align-items: center;
          margin: 2px;
        }
        .row span {
            padding-right: 40px;
        }
        .row .input {
            min-width: 48px;
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

    firstUpdated(_changedProperties) {
        super.firstUpdated(_changedProperties);
        // const event = new CustomEvent("gl-hole-first-update", {
        //     detail: {hole: this.number, offsetLeft: this.offsetLeft}, bubbles: true, composed: true
        // });
        // this.dispatchEvent(event);
    }

    _scoreChange() {
        this.dispatchEvent(new CustomEvent("gl-hole-score-change", {
            detail: this.getHoleData(), bubbles: true, composed: true
        }));
    }


    showLabel()
    {
        this.label = true;
        this.requestUpdate().then((v) => console.log(v));
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
    <div class="column">
      <div class="row">
        ${this.firstInFlexRow().map(() => html`<label>Hole:</label>`)}
        <span>${this.number}</span>
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
      <div class="row">
        <vaadin-integer-field class="input" has-controls @change="${this._scoreChange}" min="1" max="10" placeholder="3" value="${this.score}"></vaadin-integer-field>
      </div>
    </div>
    `;
    }
}

window.customElements.define("gl-hole", GlHole)
