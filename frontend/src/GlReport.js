import {css, html, LitElement} from "lit-element";
import "@vaadin/vaadin-grid/vaadin-grid.js";
import "./GlFilter.js";

export class GlReport extends LitElement {

    constructor() {
        super();
        this.addEventListener("gl-filter-submission", this._onFilterSubmission.bind(this));
    }

    _onFilterSubmission(e)
    {
        e.preventDefault();
        this.dispatchEvent(new CustomEvent("gl-request-submission", {
            detail: e.detail, bubbles: true, composed: true
        }));
    }

    render() {
        return html`
            <div class="report-container">
                <slot name="filter"></slot>
                <slot name="report"></slot>
            </div>`;
    }

}

window.customElements.define("gl-report", GlReport)