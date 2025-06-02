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
            <Link to="/appointments-patient" className="button-primary">Agendar consulta</Link>
              <dialog open>
                <div className="modal-content">
                  <form>
                   
                    <label for='especialidade'>Especialidade</label>
                    <select name="especialidade" required>
                      <option value= 'pediatria'>Pediatria</option>
                      <option value= 'cardiologia'>Cardiologia</option>
                      <option value= 'dermatologia'>Dermatologia</option>
                      <option value= 'ortopedia'>Ortopedia</option>
                      <option value= 'neurologia'>Neurologia</option>
                      <option value= 'otorrinolaringologista'>Otorrinolaringologista</option>
                      <option value= 'oftamologia'>Oftamologia</option>

                    </select>

                    <label for='Nome'>Médico</label>
                      <select name="Nome" required>
                        <option value= 'medico-1' >Medico 1</option>
                        <option value= 'medico-2'>Medico 2</option>
                      </select>

                      <label for="data">Data</label>
                      <input name="data" type="date" required id="data-consulta"></input>

                      <button className="button-primary">Agendar Consulta</button>
                  </form>
                </div>

              </dialog>
              </div>
            
            

          </div>
        </div>
      </div>
    </>
  );
}