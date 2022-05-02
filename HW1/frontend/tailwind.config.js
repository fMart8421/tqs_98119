let plugin = require('tailwindcss/plugin')

module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      colors:{
        'marine': {
          DEFAULT: '#1d2a48',
        },
      },

    },
  },
  plugins: [
      plugin(function ({ addVariant }) {
        addVariant('second', '&:nth-child(2)')
    })
  ],
}