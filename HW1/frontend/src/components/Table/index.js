import React, { Component } from 'react'
 
class Table extends Component{
  
  constructor(props){
    super(props);
    this.state = {info:props.info}
  }

  render(){
    return (
    <div className="grid grid-cols-8 gap-2 rounded-lg">
        <div className="mx-2">
          <p className="bg-marine text-white border-b-2 border-white text-center py-1 font-bold">Country</p>
          <p className="bg-marine text-white pt-1 text-right pr-2">{this.state.info.name}</p>
        </div>
        <div className="mx-2">
          <p className="bg-marine text-white border-b-2 border-white text-center py-1 font-bold">Continent</p>
          <p className="bg-marine text-white pt-1 text-right pr-2">{this.state.info.continent}</p>

        </div>
        <div className="mx-2">
          <p className="bg-marine text-white border-b-2 border-white text-center py-1 font-bold">Active Cases</p>
          <p className="bg-marine text-white pt-1 text-right pr-2">{this.state.info.activeCases}</p>

        </div>
        <div className="mx-2">
          <p className="bg-marine text-white border-b-2 border-white text-center py-1 font-bold">New Cases</p>
          <p className="bg-marine text-white pt-1 text-right pr-2">{this.state.info.newCases}</p>

        </div>
        <div className="mx-2">
          <p className="bg-marine text-white border-b-2 border-white text-center py-1 font-bold">Recovered</p>
          <p className="bg-marine text-white pt-1 text-right pr-2">{this.state.info.recovered}</p>

        </div>
        <div className="mx-2">
          <p className="bg-marine text-white border-b-2 border-white text-center py-1 font-bold">Total Cases</p>
          <p className="bg-marine text-white pt-1 text-right pr-2">{this.state.info.totalCases}</p>

        </div>
        <div className="mx-2">
          <p className="bg-marine text-white border-b-2 border-white text-center py-1 font-bold">New Deaths</p>
          <p className="bg-marine text-white pt-1 text-right pr-2">{this.state.info.newDeaths}</p>

        </div>
        <div className="mx-2">
          <p className="bg-marine text-white border-b-2 border-white text-center py-1 font-bold">Total Deaths</p>
          <p className="bg-marine text-white pt-1 text-right pr-2">{this.state.info.totalDeaths}</p>

        </div>
        

         


        
    

    </div>
  )}
}

export default Table