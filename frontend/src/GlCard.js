import {css, html, LitElement} from "lit-element";
import "@vaadin/vaadin-text-field/vaadin-text-field.js";
import "@vaadin/vaadin-text-field/vaadin-number-field.js";
import "@vaadin/vaadin-date-picker/vaadin-date-picker.js";
import "@vaadin/vaadin-text-field/vaadin-text-area.js";
import "@vaadin/vaadin-checkbox/vaadin-checkbox.js";

export class GlCard extends LitElement {

    static get styles() {
        return css`
        :host {
          font-size: 12pt;
        }

        .hole-container, .card-info-container {
          display:flex;
          flex-direction: row;
          flex-wrap:wrap;
        }

        .hole-container {
          justify-content: start;
        }

        .card-info-container {
          justify-content: start;
        }
        
        .card-container {
          display:flex;
          flex-direction: column;
          align-content:center;
        }

        .card-info {
          display:flex;
          margin: 5px;
          align-items: center;
        }

        .small-width{
          width: 130px;
        }

        vaadin-text-area {
          width:90%;
          margin:5%;
        }
        
        .card-info vaadin-text-field[name='nine'] {
            max-width: 96px;
        }

        vaadin-number-field {
          width: 48px;
        }
        `;
    }

    static get properties() {
        return {
            team: {type: Number},
            nine: {type: String},
            flight: {type: Number},
            handicap: {type: Number},
            date: {type: Date},
            name: {type: String},
            sub: {type: Boolean},
            comment: {type: String}
        };
    }

    constructor() {
        super();
        this.team = 0;
        this.flight = 0;
        this.handicap = 0;
        this.date = new Date();
        this.nine = "";
        this.name = "";
        this.sub = false;
        this.comment = "";
        this.holes = [];
        this.holeOffSets = [];
    }


    setLabel() {
        return (e) => {
            console.log(e);
            if (this.holeOffSets.length < 9) {
                this.holeOffSets.push(e.detail);
            } else {
                this.setLabels();
            }
            console.log(this.holeOffSets);
        };
    }

    connectedCallback() {
        super.connectedCallback();
        this.addEventListener("gl-hole-first-update", this.setLabel.bind(this));
    }

    firstUpdated(_changedProperties) {
        super.firstUpdated(_changedProperties);
        let slots = [...this.shadowRoot.querySelectorAll("slot")];
        let holes = slots.map(slot => slot.assignedElements()[0]);


        //TODO REDUCES HOLE ONCE PER ROW NEEDED AND POP OFF MATCHING HOLE ONE WITH LEAST OFFSETLEFT
        // let firstHole = holes.reduce((h1, h2) => {
        //     console.log(h1.getBoundingClientRect().left);
        //     return h1.h1.getBoundingClientRect().left <= h2.h1.getBoundingClientRect().left ? h2 : h1
        // });

        let firstHole = holes[0];

        // firstHole.showLabel();

        // let boundingClientRect = this.getBoundingClientRect();
        // let height = boundingClientRect.bottom - boundingClientRect.top;
        let boundingClientRect = this.shadowRoot.querySelector('.hole-container').getBoundingClientRect();
        let maxHolesPerRow = Math.floor((boundingClientRect.right - boundingClientRect.left) / 104);
        let remainder = Math.floor(9 % maxHolesPerRow);
        // 1 row = hole 1
        // 2 row

        //TODO INTERATE A LOOP 9 - maxHolesPerRow until the remainder is less than maxHolesPerRow

        let firstHolesInRow = [];
        for(let x = 0; x < 9; x++)
        {

        }

        let y = 9 + maxHolesPerRow;
        while (y > maxHolesPerRow)
        {
            y -= maxHolesPerRow;
            holes[9 - y].showLabel();
        }

        for(let x = 9; x > maxHolesPerRow; x = x - maxHolesPerRow)
        {
            console.log(x);
            let element = holes[Math.floor(9 / x)];
            console.log(element);
            // element.showLabel();
        }

        console.log(remainder);


        //todo first hole always has label. Then calculate how many rows are needed.

        let holeLabel = Math.floor(((boundingClientRect.right - boundingClientRect.left) / 104));


        // holes[holeLabel].showLabel();

        // console.log(boundingClientRect.top, boundingClientRect.bottom);

        // holes.forEach(h => h.showLabel());
        // this.shadowRoot.querySelectorAll("slot").forEach(slot => {
        //     this.holes.push(slot.assignedElements());
        // });

        // const firstHolesInRow = [];
        // // TODO LOOP FOR AS MANY ROWS NEEDED
        // console.log(this.holes[0].offsetLeft);
        // let offSets = this.holes.map(h => h.offsetLeft);
        // console.log(offSets);
        // let items = offSets
        //     .reduce((o1, o2) => {return o1 <= o2 ? o1 : o2});
        // console.log(items);
        // firstHolesInRow.push(items);
        // firstHolesInRow.forEach(hole => console.log(hole));
        // console.log(firstHolesInRow);
    }

    // _assignHoleOffsets() {
    //     return (e) => {
    //         this.holeOffSets.push(e.detail);
    //         this.setLabels();
    //     };
    // }

    // setLabels(e) {
    //     console.log(e);
    //     const firstHolesInRow = [];
    //     console.log("TESTSKDHFSKDHFSHDF");
    //     // TODO LOOP FOR AS MANY ROWS NEEDED
    //     console.log(this.holes[0].offsetLeft);
    //     let offSets = this.holes.map(h => h.offsetLeft);
    //     console.log(offSets);
    //     let items = offSets
    //         .reduce((o1, o2) => {return o1 <= o2 ? o1 : o2});
    //     console.log(items);
    //     firstHolesInRow.push(items);
    //     firstHolesInRow.forEach(hole => console.log(hole));
    //     console.log(firstHolesInRow);
    // }

    formattedDate() {
        try {
            return this.date.toISOString().split("T")[0];
        } catch (e) {
            return this.date;
        }
    }

    submit() {

        // Collect hole data.
        const holeData = []
        this.shadowRoot.querySelectorAll("slot").forEach(slot => {
            holeData.push(slot.assignedElements()[0].getHoleData());
        });

        // Dispatch an application event.
        this.dispatchEvent(new CustomEvent("gl-hole-card-submission", {
            detail: {
                flight: this.flight,
                date: this.date,
                nine: this.nine,
                team: this.team,
                name: this.name,
                sub: this.sub,
                handicap: this.handicap,
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

    _updateDate() {
        this.date = new Date(`${this.shadowRoot.querySelector("vaadin-date-picker").value}T12:00:00Z`);
    }

    render() {
        return html`
    <div class="card-container">
      <div class="card-info-container">        
        <div class="card-info">
          <label for="nine">Nine:</label>
          <vaadin-text-field class="small-width" name="nine" value="${this.nine}" ></vaadin-text-field>
        </div>
        <div class="card-info">
          <label for="date">Date:</label>
          <vaadin-date-picker class="small-width" name="date" @change="${this._updateDate}" value="${this.formattedDate()}" ></vaadin-date-picker>
        </div>
        <div class="card-info">
          <label for="name">Name:</label>
          <vaadin-text-field name="name" value="${this.name}" ></vaadin-text-field>
        </div>
        <div class="card-info">
          <label for="sub">sub</label>
          <vaadin-checkbox name="sub" value="${this.sub}" ></vaadin-checkbox>
        </div>
        <div class="card-info">
          <label for="flight">Flight:</label>
          <vaadin-number-field name="flight" value="${this.flight}" ></vaadin-number-field>
        </div>
        <div class="card-info">
          <label for="team">Team #:</label>
          <vaadin-number-field name="team" value="${this.team}" ></vaadin-number-field>
        </div>
        <div class="card-info">
          <label for="handicap">hndcp:</label>
          <vaadin-number-field name="handicap" value="${this.handicap}" ></vaadin-number-field>
        </div>
      </div>
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
      <vaadin-text-area label="Comments:" name="comment"></vaadin-text-area>
      <vaadin-button @click="${this.submit}">Update</vaadin-button>
    </div>
    `;
    }

}

window.customElements.define("gl-card", GlCard)