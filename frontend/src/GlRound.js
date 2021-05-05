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

        .hole-container {
          display:flex;
          flex-direction: row;
          justify-content: start;
        }
        
        .round-container {
          display:flex;
          flex-direction: column;
          align-content:center;
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
            comment: {type: String},
            score: {type: Array}
        };
    }

    constructor() {
        super();
        this.comment = "";
        this.score = [0, 0, 0, 0, 0, 0, 0, 0, 0];
    }

    connectedCallback() {
        super.connectedCallback();
        this.addEventListener("gl-hole-first-update", this.setLabelsOnFirstInRow.bind(this));
    }

    setLabelsOnFirstInRow(e) {
        if(e.detail.hole === 9){
            const slots = [...this.shadowRoot.querySelectorAll("slot")];
            const holes = slots.map(slot => slot.assignedElements()[0]);
            const holeContainer = this.shadowRoot.querySelector('.hole-container').getBoundingClientRect();
            const widthOfElement = holes[0].getBoundingClientRect().width;
            let maxHolesPerRow = Math.floor((holeContainer.right - holeContainer.left) / widthOfElement);
            let y = 9 + maxHolesPerRow;
            while (y > maxHolesPerRow) {
                y -= maxHolesPerRow;
                const firstHoleInRow = holes[9 - y];
                firstHoleInRow.showLabel();
            }
        }
    }

    _getHoles() {
        const holeData = [];
        this.shadowRoot.querySelectorAll("slot").forEach(slot => {
            holeData.push(slot.assignedElements()[0].getHoleData());
        });
        return holeData;
    }

    submit() {
        const holeData = this._getHoles();
        // Dispatch an application event.
        this.dispatchEvent(new CustomEvent("gl-hole-card-submission", {
            detail: {
                comment: this.comment,
                hole1: holeData[0],
                hole2: holeData[1],
                hole3: holeData[2],
                hole4: holeData[3],
                hole5: holeData[4],
                hole6: holeData[5],
                hole7: holeData[6],
                hole8: holeData[7],
                hole9: holeData[8],
            }, bubbles: true, composed: true
        }));
    }

    render() {
        return html`
            <div class="round-container">
                <div class="hole-container">
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
                <vaadin-text-area class="comments" label="Comments:" name="comment"></vaadin-text-area>
                <vaadin-button @click="${this.submit}">Update</vaadin-button>
            </div>
        `;
    }

}

window.customElements.define("gl-round", GlRound)