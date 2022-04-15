import React from 'react'
import Select from '../../components/Select'

const countries = ["Portugal", "Spain", "France", "USA",]
const cities = ['Braga', 'Viseu', 'Coimbra', 'Aveiro']

const Home = () => {
    return (
        <div className="">
            <div className=" bg-red-900 py-4">
                <p className="ml-4 text-4xl font-extrabold text-white">
                    DailyVid
                </p>
            </div>
            <div className="flex mx-auto">
                <div className="mx-4">
                    <Select options={countries} label="Select the Country" identifier="countries"></Select>
                </div>
                <div className="mx-4">
                    <Select options={cities} label="Select the city/state" identifier="cities"></Select>
                </div>


            </div>
        </div>
    )
}

export default Home