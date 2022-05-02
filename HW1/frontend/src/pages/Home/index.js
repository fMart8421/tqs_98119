import React, { Component } from 'react';
import Table from '../../components/Table'


class Home extends Component {
    constructor(props) {
        super(props);
        this.state = {
            date: false,
            displayTable:false,
            info:{}
        }
    }


    fetchCountry = () => {
        this.setState({displayTable:false})
        var country = document.getElementById("searchText").value;
        if(country.length>15) {
            alert("There is no country with more than 15 characters, please write a proper country name")
            console.log(country.length)
            this.setState({displayTable:false})
            return
        }


        if (!this.state.date) {

            fetch("http://localhost:8080/api/country?name=" + country).then((response) => response.json()).then((json) => {
                console.log(json)
                this.setState({info:json, displayTable:true})
            

            })

        } else{
            let year = document.getElementById("searchYear").value
            let month = document.getElementById("searchMonth").value
            let day = document.getElementById("searchDay").value
            if(Number.parseInt(day)<10) day='0'+day 
            if(Number.parseInt(month)<10) month='0'+month 

            let date=year+'-'+month+'-'+day

            console.log("fetching", country, date)
            
            fetch("http://localhost:8080/api/country-date?name=" + country+"&date="+date).then((response) => response.json()).then((json) => {
            console.log(json)
            this.setState({info:json, displayTable:true})
        })
        
        }

    }

    render() {
        return (
            <div className="bg-blue-200 rounded-lg absolute active left-1/2 top-1/2 -translate-y-1/2 -translate-x-1/2 flex flex-col h-3/4 min-w-max">
                <div className="bg-marine py-4 rounded-t-lg">
                    <p className="ml-4 text-4xl font-extrabold text-white">
                        InciVID19
                    </p>
                </div>
                <div className="flex mt-16">
                    <input id="searchText" type="text" placeholder="Search for a country" className="w-72 ml-64 mr-8 rounded-xl px-3 py-1 outline-none focus:shadow"></input>
                    <div className="flex items-center mr-1">
                        <input onClick={() => { this.setState({ date: document.getElementById("date").checked }); console.log(this.state.date) }} className="mr-1" type="checkbox" id="date"></input> <label htmlFor="date" className="text-marine">Show Date</label>
                    </div>
                    {this.state.date ?
                        <div>
                            <input id="searchYear" type="number" min="2020" max="2022" placeholder="Year" className="mx-1 rounded-xl px-3 py-1 outline-none focus:shadow"></input>
                            <input id="searchMonth" type="number" min="01" max="12" placeholder="Month" className="mx-1 rounded-xl px-3 py-1 outline-none focus:shadow"></input>
                            <input id="searchDay" type="number" min="01" max="31" placeholder="Day" className="mx-1 rounded-xl px-3 py-1 outline-none focus:shadow"></input>
                        </div>
                        : <></>
                    }


                    <button className="mr-64 ml-8 rounded-2xl bg-blue-700/40 px-4 text-white active:scale-95" onClick={() => this.fetchCountry()}>Search</button>

                </div>
                
                {this.state.displayTable && <div className="mt-32">
                    <Table info={this.state.info}></Table>
                </div>}
            </div>
        );
    }
}

export default Home;