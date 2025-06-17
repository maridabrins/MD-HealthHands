import "./appointments.css";
import { useState, useEffect } from "react";
import lupa from "../../assets/lupa.svg";
import editar from "../../assets/editar.svg";
import excluir from "../../assets/Cancel.svg";
import Modal from "../../components/Modal/modal";
import { toast } from "react-toastify";
import api from "../../services/axiosConfig";

export default function Appointments() {
  const [modalAberto, setModalAberto] = useState(false);
  const [especialidade, setEspecialidade] = useState("");
  const [medicoId, setMedicoId] = useState("");
  const [dataConsulta, setDataConsulta] = useState("");
  const [medicosDisponiveis, setMedicosDisponiveis] = useState([]);
  const [tipoUsuario, setTipoUsuario] = useState("");
  const [consultas, setConsultas] = useState([]);
  const [modalEditarAberto, setModalEditarAberto] = useState(false);
const [modalCancelarAberto, setModalCancelarAberto] = useState(false);
const [consultaSelecionada, setConsultaSelecionada] = useState(null);
const [novaData, setNovaData] = useState("");

  // Primeiro useEffect: busca médicos e define tipo de usuário
  useEffect(() => {
    const token = localStorage.getItem("token");
    const tipo = localStorage.getItem("tipo");
    setTipoUsuario(tipo);

    api
      .get("/medico/all", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => setMedicosDisponiveis(res.data))
      .catch((error) => {
        console.error("Erro ao buscar médicos:", error);
        toast.error("Erro ao buscar médicos.");
      });
  }, []);

  // Segundo useEffect: carrega as consultas quando tipoUsuario estiver definido
  useEffect(() => {
  if (!tipoUsuario) return;

  const token = localStorage.getItem("token");
  const userId = localStorage.getItem("userId");

  if (tipoUsuario === "PACIENTE") {
    api
      .get("/consultas/all", {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((res) => setConsultas(res.data))
      .catch((err) => {
        console.error("Erro ao buscar consultas do paciente:", err);
        toast.error("Erro ao carregar suas consultas.");
      });
  } else if (tipoUsuario === "MEDICO") {
    api
      .get(`/consultas/medico/${userId}`, {
        headers: { Authorization: `Bearer ${token}` },
      })
      .then((res) => setConsultas(res.data))
      .catch((err) => {
        console.error("Erro ao buscar consultas do médico:", err);
        toast.error("Erro ao carregar suas consultas.");
      });
  }
}, [tipoUsuario]);

  const handleAgendar = async (e) => {
    e.preventDefault();
    const token = localStorage.getItem("token");
    const pacienteId = parseInt(localStorage.getItem("userId"));

    if (tipoUsuario !== "PACIENTE") {
      toast.error("Apenas pacientes podem agendar consultas.");
      return;
    }

    try {
      await api.post(
        "/consultas/agendar",
        {
          especialidade,
          medicoId: parseInt(medicoId),
          pacienteId,
          data: dataConsulta,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      toast.success("Consulta agendada com sucesso!");
      setModalAberto(false);
      setEspecialidade("");
      setMedicoId("");
      setDataConsulta("");

      // Atualiza lista após agendamento
      const res = await api.get("/consultas/all", {
        headers: { Authorization: `Bearer ${token}` },
      });
      setConsultas(res.data);
    } catch (error) {
      console.error(error);
      const mensagemErro =
        error.response?.data || error.message || "Erro ao agendar consulta.";
      toast.error(mensagemErro);
    }
  };

  const handleEditarConsulta = async (e) => {
  e.preventDefault();
  const token = localStorage.getItem("token");
  try {
    await api.put(`/consultas/update/${consultaSelecionada.id}`, 
      {
        data: novaData
      },
      {
      headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      },
      }
    );
    toast.success("Consulta remarcada com sucesso!");
    setModalEditarAberto(false);
    
  } catch (error) {
    toast.error("Erro ao remarcar consulta.");
  }
};

const handleConfirmarCancelamento = async () => {
  const token = localStorage.getItem("token");
  try {
    await api.delete(`/consultas/delete/${consultaSelecionada.id}`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    });
    toast.success("Consulta cancelada com sucesso!");
    setModalCancelarAberto(false);
    
  } catch (error) {
    toast.error("Erro ao cancelar consulta.");
  }
};
function formatarData(dataStr) {
  const [ano, mes, dia] = dataStr.split("-");
  return `${dia}/${mes}/${ano}`;
}


  return (
    <div className="appointments-container">
      <div className="appointments-content">
        <img src={lupa} alt="ícone de lupa" />
        <input type="search" placeholder="Buscar por consulta ou médico" />

        <div className="appointments-cards">
          <div className="appointments-infos">
            {consultas.length > 0 ? (
              <div className="appointments-table">
                <table>
                  <thead>
                    <tr>
                      <th className="th-especialidade">Especialidade</th>
                      <th className="th-nome">Médico</th>
                      <th className="th-data">Data</th>
                      <th className="th-acoes">Ações</th>
                    </tr>
                  </thead>
                  <tbody>
                    {consultas.map((consulta) => (
                      <tr key={consulta.id}>
                        <td>{consulta.especialidade}</td>
                        <td>{consulta.medicoNome}</td>
                        
                          <td>{formatarData(consulta.data)}</td>
                        
                        <td className="acoes">
                         <button
  className="button-edit"
  onClick={() => {
    setConsultaSelecionada(consulta);
    setNovaData(consulta.data.split("T")[0]); // se vier com T00:00:00
    setModalEditarAberto(true);
  }}
>
  <img src={editar} alt="Editar" />
</button>

<button
  className="button-delete"
  onClick={() => {
    setConsultaSelecionada(consulta);
    setModalCancelarAberto(true);
  }}
>
  <img src={excluir} alt="Excluir" />
</button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            ) : (
              <h2>
                {tipoUsuario === "MEDICO"
                  ? "Você ainda não tem consultas agendadas."
                  : "Você ainda não agendou nenhuma consulta."}
              </h2>
            )}

            {tipoUsuario === "PACIENTE" && (
              <button
                className="button-primary"
                onClick={() => setModalAberto(true)}
              >
                Agendar consulta
              </button>
            )}

            <Modal isOpen={modalAberto} onClose={() => setModalAberto(false)}>
              <form onSubmit={handleAgendar}>
                <label htmlFor="especialidade">Especialidade</label>
                <select
                  name="especialidade"
                  required
                  value={especialidade}
                  onChange={(e) => {
                    setEspecialidade(e.target.value);
                    setMedicoId("");
                  }}
                >
                  <option value="">Selecione</option>
                  <option value="PEDIATRIA">Pediatria</option>
                  <option value="CARDIOLOGIA">Cardiologia</option>
                  <option value="DERMATOLOGIA">Dermatologia</option>
                  <option value="ORTOPEDIA">Ortopedia</option>
                  <option value="NEUROLOGIA">Neurologia</option>
                  <option value="OTORRINOLARINGOLOGISTA">Otorrino</option>
                  <option value="OFTAMOLOGISTA">Oftalmologia</option>
                </select>

                <label htmlFor="medico">Médico</label>
                <select
                  name="medico"
                  required
                  value={medicoId}
                  onChange={(e) => setMedicoId(e.target.value)}
                  disabled={!especialidade}
                >
                  <option value="">Selecione</option>
                  {medicosDisponiveis
                    .filter((m) => m.especialidade === especialidade)
                    .map((m) => (
                      <option key={m.id} value={m.id}>
                        {m.nome}
                      </option>
                    ))}
                </select>

                <label htmlFor="data">Data</label>
                <input
                  name="data"
                  type="date"
                  required
                  id="data-consulta"
                  value={dataConsulta}
                  onChange={(e) => setDataConsulta(e.target.value)}
                />

                <div className="botoes-modal">
                  <button type="submit" className="button-primary">
                    Agendar Consulta
                  </button>
                  <button
                    type="button"
                    className="button-secondary"
                    onClick={() => setModalAberto(false)}
                  >
                    Cancelar
                  </button>
                </div>
              </form>
            </Modal>
            {/* Modal de remarcar consulta */}
<Modal isOpen={modalEditarAberto} onClose={() => setModalEditarAberto(false)}>
  <form onSubmit={handleEditarConsulta}>
    <h3>Remarcar Consulta</h3>
    <label htmlFor="novaData">Nova Data:</label>
    <input
      type="date"
      id="novaData"
      value={novaData}
      onChange={(e) => setNovaData(e.target.value)}
      required
    />
    <div className="botoes-modal">
      <button type="submit" className="button-primary">
        Confirmar
      </button>
      <button
        type="button"
        className="button-secondary"
        onClick={() => setModalEditarAberto(false)}
      >
        Cancelar
      </button>
    </div>
  </form>
</Modal>

{/* Modal de confirmação de cancelamento */}
<Modal isOpen={modalCancelarAberto} onClose={() => setModalCancelarAberto(false)}>
  <div>
    <h3>Cancelar Consulta</h3>
    <p>Tem certeza que deseja cancelar esta consulta?</p>
    <div className="botoes-modal">
      <button
        className="button-primary"
        onClick={handleConfirmarCancelamento}
      >
        Sim, cancelar
      </button>
      <button
        className="button-secondary"
        onClick={() => setModalCancelarAberto(false)}
      >
        Voltar
      </button>
    </div>
  </div>
</Modal>

          </div>
        </div>
      </div>
    </div>
  );
}
