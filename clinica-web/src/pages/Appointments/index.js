import "./appointments.css"

import { Link } from 'react-router-dom';

import lupa from "../../assets/lupa.svg"


export default function Appointments() {
  return (
    <>
     <div className='appointments-container'>
        <div className='appointments-content'>
          <img src={lupa} alt="icone de lupa"></img>
          <input type="search" placeholder="Buscar por consulta ou médico"></input>
          <div className='appointments-cards'>

              <div className="appointments-infos">
                <h2>Não há consultas no momento</h2>
            <Link to="/" className="button-primary">Agendar consulta</Link>
              
              </div>
            
            

          </div>
        </div>
      </div>
    </>
  );
}