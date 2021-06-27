import {css, html, LitElement} from "lit-element";
import "@vaadin/vaadin-text-field/vaadin-text-field.js";
import "@vaadin/vaadin-text-field/vaadin-number-field.js";
import "@vaadin/vaadin-date-picker/vaadin-date-picker.js";
import "@vaadin/vaadin-text-field/vaadin-text-area.js";
import "@vaadin/vaadin-checkbox/vaadin-checkbox.js";

export class GlRound extends LitElement {

    static get styles() {
        return css`
        :host {
          font-size: 12pt;
          --lumo-disabled-text-color: var(--lumo-contrast-70pct);
        }
        
        .display-field::part(input-field) {
            background-color: var(--lumo-shade-10pct);
        }
        
        .round-container {
          display:flex;
          flex-direction: row;
          align-content:center;
          flex-wrap: wrap;
        }        
        
        .report-container {
          display:flex;
          flex-direction: row;
          align-content:center;
          flex-wrap: wrap;
        }
        
        .display-field {
            width: 50px;
            margin-left: 2px;
            margin-right:2px;
        }

        .comments {
            margin:0;
            width:90%;
            margin:5%;
        }
        
        .row {
            display:flex;
            flex-direction:row;
            flex-wrap:wrap;
        }
        `;
    }

    static get properties() {
        return {
            handicap: {type: Number},
            grossScore: {type: Number, attribute: "gross-score"},
            netScore: {type: Number, attribute: "net-score"},
            netPoints: {type: Number, attribute: "net-points"},
            matchPoints: {type: Number, attribute: "match-points"},
            teamNet: {type: Number, attribute: "team-net"}
        };
    }

    constructor() {
        super();
        this.handicap = null;
        this.grossScore = null;
        this.netScore = null;
        this.netPoints = null;
        this.matchPoints = null;
        this.teamNet = null;
        this.round = [{},{},{},{},{},{},{},{},{}];
    }

    connectedCallback() {
        super.connectedCallback();
        this.addEventListener("gl-hole-first-update", this._onHoleFirstUpdate.bind(this));
        this.addEventListener("gl-hole-score-change", this._onHoleScoreChange.bind(this));
    }

    _onHoleFirstUpdate(e) {
        if(e.detail.hole === 9){
            // Calculate which holes are first in the row then call show label on those holes.
            const slots = [...this.shadowRoot.querySelector(".round-container").querySelectorAll("slot")];
            const holes = slots.map(slot => slot.assignedElements()[0]);
            const left = holes.sort((a,b) => { return a.getBoundingClientRect().left - b.getBoundingClientRect().left;})[0].getBoundingClientRect().left;
            holes.forEach((hole) => {if(hole.getBoundingClientRect().left === left){hole.showLabel()}});
        }
    }

    _onHoleScoreChange(e) {
        this.round[e.detail.hole - 1] = e.detail;
        this.dispatchEvent(new CustomEvent("gl-round-score-change", {
            detail: {round:this.round}, bubbles: true, composed: true
        }));
    }

    render() {
        return html`
            <div class="row">
                <div class="round-container">
                    <slot name="hole1"></slot>
                    <slot name="hole2"></slot>
                    <slot name="hole3"></slot>
                    <slot name="hole4"></slot>
                    <slot name="hole5"></slot>
                    <slot name="hole6"></slot>
                    <slot name="hole7"></slot>
                    <slot name="hole8"></slot>
                    <slot name="hole9"></slot>
                </div>
                <div class="report-container">
                    <slot name="grossScore1"></slot>
                    <slot name="grossScore2"></slot>
                    <slot name="grossScore3"></slot>
                    <slot name="grossScore4"></slot>
                    <slot name="grossScore5"></slot>
                    <slot name="grossScore6"></slot>
                    <slot name="grossScore7"></slot>
                    <slot name="grossScore8"></slot>
                    <slot name="grossScore9"></slot>
                    ${this.grossScore !== null ? [this.grossScore].map(score => html`<vaadin-number-field class="display-field" name="score" label="Gross" value="${score}" disabled></vaadin-number-field>`):``}
                    ${this.handicap !== null ? [this.handicap].map(score => html`<vaadin-number-field class="display-field" name="score" label="Hdcp" value="${score}" disabled></vaadin-number-field>`):``}
                    ${this.netScore !== null ? [this.netScore].map(score => html`<vaadin-number-field class="display-field" name="score" label="Net" value="${score}" disabled></vaadin-number-field>`):``}
                </div>
                <div class="report-container">
                    <slot name="netScore1"></slot>
                    <slot name="netScore2"></slot>
                    <slot name="netScore3"></slot>
                    <slot name="netScore4"></slot>
                    <slot name="netScore5"></slot>
                    <slot name="netScore6"></slot>
                    <slot name="netScore7"></slot>
                    <slot name="netScore8"></slot>
                    <slot name="netScore9"></slot>
                    ${this.netPoints !== null ? [this.netPoints].map(points => html`<vaadin-number-field class="display-field" name="score" label="Net" value="${points}" disabled></vaadin-number-field>`):``}
                    ${this.matchPoints !== null ? [this.matchPoints].map(points => html`<vaadin-number-field class="display-field" name="score" label="Match" value="${points}" disabled></vaadin-number-field>`):``}
                    ${this.teamNet !== null ? [this.teamNet].map(points => html`<vaadin-number-field class="display-field" name="score" label="Team" value="${points}" disabled></vaadin-number-field>`):``}
                </div>
            </div>
        `;
    }

}

window.customElements.define("gl-round", GlRound)