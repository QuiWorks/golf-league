import {css, html, LitElement} from "lit-element";
import "@vaadin/vaadin-text-field/vaadin-integer-field.js";

export class GlScore extends LitElement {

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
        this.par = 0;
        this.handicap = 0;
        this.score = 0;
    }

    getHoleData()
    {
        return {
            score: this.score,
            hole: this.number,
            par: this.par,
            handicap: this.handicap
        }
    }

    render() {
        return html`
    <div class="row">
        <vaadin-integer-field class="input" has-controls @change="${this._scoreChange}" min="1" max="10" placeholder="3" value="${this.score}"></vaadin-integer-field>
    </div>
    `;
    }
}

window.customElements.define("gl-score", GlScore)
