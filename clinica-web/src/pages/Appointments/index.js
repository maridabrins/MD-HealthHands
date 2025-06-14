import "./appointments.css";
import { useState, useEffect } from "react";
import lupa from "../../assets/lupa.svg";
import Modal from "../../components/Modal/modal";
import { toast } from "react-toastify";

export default function Appointments() {
  const [modalAberto, setModalAberto] = useState(false);
  const [especialidade, setEspecialidade] = useState("");
  const [medicoId, setMedicoId] = useState("");
  const [dataConsulta, setDataConsulta] = useState("");
  const [medicosDisponiveis, setMedicosDisponiveis] = useState([]);
  const [tipoUsuario, setTipoUsuario] = useState(""); // paciente ou medico

  useEffect(() => {
    const token = localStorage.getItem("token");
    const tipo = localStorage.getItem("tipo"); 
    setTipoUsuario(tipo);

    fetch("http://localhost:8080/medico/all", {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((res) => {
        if (!res.ok) throw new Error("Erro ao buscar médicos");
        return res.json();
      })
      .then((data) => setMedicosDisponiveis(data))
      .catch((error) => {
        console.error("Erro ao buscar médicos:", error);
        toast.error("Erro ao buscar médicos.");
      });
  }, []);

  const handleAgendar = async (e) => {
    e.preventDefault();

    const token = localStorage.getItem("token");

    if (tipoUsuario !== "PACIENTE") {
      toast.error("Apenas pacientes podem agendar consultas.");
      return;
    }

    try {
      const response = await fetch("http://localhost:8080/consultas/agendar", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify({
          especialidade,
          medicoId: parseInt(medicoId),
          data: dataConsulta,
        }),
      });

      if (!response.ok) {
        const erro = await response.text();
        throw new Error(erro);
      }

      toast.success("Consulta agendada com sucesso!");
      setModalAberto(false);
      setEspecialidade("");
      setMedicoId("");
      setDataConsulta("");
    } catch (error) {
      console.error(error);
      toast.error(error.message || "Erro ao agendar consulta.");
    }
  };

  return (
    <div className="appointments-container">
      <div className="appointments-content">
        <img src={lupa} alt="ícone de lupa" />
        <input type="search" placeholder="Buscar por consulta ou médico" />

        <div className="appointments-cards">
          <div className="appointments-infos">
            <h2>
              {tipoUsuario === "MEDICO"
                ? "Aqui aparecerão suas consultas agendadas."
                : "Não há consultas no momento"}
            </h2>

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
          </div>
        </div>
      </div>
    </div>
  );
}
