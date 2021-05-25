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

        .comments {
            margin:0;
            width:90%;
            margin:5%;
        }
        `;
    }

    constructor() {
        super();
        this.round = [{},{},{},{},{},{},{},{},{}];
    }

    connectedCallback() {
        super.connectedCallback();
        this.addEventListener("gl-hole-first-update", this.setLabelsOnFirstInRow.bind(this));
        this.addEventListener("gl-hole-score-change", this._onHoleScoreChange.bind(this));
    }

    setLabelsOnFirstInRow(e) {
        if(e.detail.hole === 9){
            // Calculate which holes are first in the row then call show label on those holes.
            const slots = [...this.shadowRoot.querySelectorAll("slot")];
            const holes = slots.map(slot => slot.assignedElements()[0]);
            const holeContainer = this.shadowRoot.querySelector('.round-container').getBoundingClientRect();
            const widthOfElement = holes[0].getBoundingClientRect().width;
            const maxWidthOfLabeledHole = 100;
            const maxHolesPerRow = Math.floor(((holeContainer.right - holeContainer.left) - (maxWidthOfLabeledHole - widthOfElement)) / widthOfElement);
            let y = 9 + maxHolesPerRow;
            while (y > maxHolesPerRow) {
                y -= maxHolesPerRow;
                const firstHoleInRow = holes[9 - y];
                firstHoleInRow.showLabel();
            }
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
        `;
    }

}

window.customElements.define("gl-round", GlRound)