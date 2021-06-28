import {css, html, LitElement} from "lit-element";
import "@vaadin/vaadin-grid/vaadin-grid.js";
import "./GlCard.js";
import "./GlGolfer.js";
import "./GlRound.js";
import "./GlScore.js";

export class GlReport extends LitElement {

    static get styles() {
        return css`
        `;
    }

    static get properties() {
        return {};
    }

    constructor() {
        super();
        this.items = [
            {
                g1: 3,
                g2: 3,
                g3: 3,
                g4: 3,
                g5: 3,
                g6: 3,
                g7: 3,
                g8: 3,
                g9: 3,
                grossScore: 36,
                handicap: 4,
                newScore: 32,
                n1: 3,
                n2: 3,
                n3: 3,
                n4: 3,
                n5: 3,
                n6: 3,
                n7: 3,
                n8: 3,
                n9: 3,
                netPoints: 2,
                matchPoints: 2,
                teamNet: 1
            }
        ]
    }

    render() {
        return html`
            <div class="report-container">
                <vaadin-grid items="${this.items}">
                    <vaadin-grid-column>
                        <template>
                            <gl-golfer>
                                <gl-round>
                                    <gl-score></gl-score>
                                </gl-round>
                            </gl-golfer>
                        </template>
                    </vaadin-grid-column>
                </vaadin-grid>
            </div>`;
    }

}

window.customElements.define("gl-report", GlReport)