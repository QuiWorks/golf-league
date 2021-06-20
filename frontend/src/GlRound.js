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

        .comments {
            margin:0;
            width:90%;
            margin:5%;
        }
        `;
    }

    static get properties() {
        return {
            handicap: {type: Number},
            grossScore: {type: Number},
            netScore: {type: Number},
            netPoints: {type: Number},
            matchPoints: {type: Number},
            teamNet: {type: Number}
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
                ${[this.grossScore].map(score => html`<span>${score}</span>`)}
                ${[this.grossScore].map(score => html`<span>${score}</span>`)}
                ${[this.netScore].map(score => html`<span>${score}</span>`)}
                <slot name="netScore1"></slot>
                <slot name="netScore2"></slot>
                <slot name="netScore3"></slot>
                <slot name="netScore4"></slot>
                <slot name="netScore5"></slot>
                <slot name="netScore6"></slot>
                <slot name="netScore7"></slot>
                <slot name="netScore8"></slot>
                <slot name="netScore9"></slot>
                ${[this.netPoints].map(points => html`<span>${points}</span>`)}
                ${[this.matchPoints].map(points => html`<span>${points}</span>`)}
                ${[this.teamNet].map(points => html`<span>${points}</span>`)}
            </div>
        `;
    }

}

window.customElements.define("gl-round", GlRound)