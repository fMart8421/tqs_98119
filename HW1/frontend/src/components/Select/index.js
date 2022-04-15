import React from 'react'

const Select = ({options, label, identifier}) => {
  return (
    <div className="flex flex-col">
        <label htmlFor={identifier} className="text-lg font-medium">{label}</label>
        <select id={identifier} className=" border-2 rounded-lg hover:border-red-900">
            <option className="rounded-lg"></option>
            {options.map((op, i) =>{
                return(
                    <option className="rounded-lg" key={i} value={op}>{op}</option>
                )
            })}
        </select>
    

    </div>
  )
}

export default Select