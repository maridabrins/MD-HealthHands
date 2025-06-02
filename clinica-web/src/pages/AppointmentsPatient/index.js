import "./appointmentsPatient.css"

import { Link } from 'react-router-dom';

 import lupa from "../../assets/lupa.svg"
import editar from "../../assets/editar.svg"
import excluir from "../../assets/Cancel.svg"


export default function AppointmentsPatient() {
  return (
    <>
     <div className='appointments-container'>
        <div className='appointments-content'>
          <img src={lupa} alt="icone de lupa"></img>
          <input type="search" placeholder="Buscar por consulta ou médico"></input>
          <div className='appointments-cards'>

              <div className="appointments-table">
                <table>
                    <tr>
                        <th className="th-especialidade">Especialidade</th>
                        <th className="th-nome">Médico</th>
                        <th className="th-data">Data</th>
                        <th className="th-acoes">Ações</th>
                    </tr>
                    <tr>
                        <td>Otorrinolaringologista</td>
                        <td>Médico 1</td>
                        <td>10/06/25</td>
                        <td className="acoes">
                            <button className="button-edit"><img src={editar}></img></button>
                            <button className="button-delete"><img src={excluir}></img></button>
                        </td>
                    </tr>
                </table>

                
  <hr class="divider" />
                 
            <Link to="/appointments-patient" className="button-primary">Agendar consulta</Link>
              
              </div>
            
            

          </div>
        </div>
      </div>
</>
  );
}