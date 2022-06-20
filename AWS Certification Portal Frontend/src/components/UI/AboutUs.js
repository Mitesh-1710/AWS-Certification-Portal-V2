import React from 'react'
import '../Global.css'
import about from '../UI/aboutUs.JPG'

// AboutUs Component
function AboutUs() {
  return (
    <section className="container">
        <div className="about">
            <img src={about} className="about-img" alt="About Us" title="About Us"/>
        </div>
    </section>
  )
}

export default AboutUs