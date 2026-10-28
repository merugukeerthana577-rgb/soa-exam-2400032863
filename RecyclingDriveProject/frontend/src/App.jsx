
import { useEffect, useState } from 'react';

const API = '/RecyclingDrive';

function App() {
  const [drives, setDrives] = useState([]);
  const [form, setForm] = useState({
    driveLocation: '',
    materialType: '',
    collectedKg: '',
    completed: 'false'
  });
  const [loading, setLoading] = useState(false);
  const [saving, setSaving] = useState(false);
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');

  async function loadDrives() {
    setLoading(true);
    setError('');
    try {
      const response = await fetch(`${API}/DisplayAll`);
      if (!response.ok) throw new Error('Could not load records.');
      const data = await response.json();
      setDrives(Array.isArray(data) ? data : []);
    } catch {
      setError('Unable to connect to the backend. Check that Spring Boot is running and the API URL is correct.');
    } finally {
      setLoading(false);
    }
  }

  useEffect(() => {
    loadDrives();
  }, []);

  function handleChange(e) {
    setForm({ ...form, [e.target.name]: e.target.value });
  }

  async function addDrive(e) {
    e.preventDefault();
    setMessage('');
    setError('');

    if (!form.driveLocation.trim() || !form.materialType.trim() ||
        form.collectedKg === '' || Number(form.collectedKg) < 0) {
      setError('Enter all fields correctly. Kilograms cannot be negative.');
      return;
    }

    setSaving(true);
    try {
      const response = await fetch(`${API}/Add`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          driveLocation: form.driveLocation.trim(),
          materialType: form.materialType.trim(),
          collectedKg: Number(form.collectedKg),
          completed: form.completed === 'true'
        })
      });

      if (!response.ok) {
        const details = await response.text();
        throw new Error(details || 'Failed to add recycling drive.');
      }

      setMessage('Recycling drive added successfully!');
      setForm({
        driveLocation: '',
        materialType: '',
        collectedKg: '',
        completed: 'false'
      });
      await loadDrives();
    } catch (err) {
      setError(err.message || 'Unable to add record.');
    } finally {
      setSaving(false);
    }
  }

  async function deleteDrive(id) {
    if (!window.confirm(`Delete recycling drive #${id}?`)) return;
    setMessage('');
    setError('');

    try {
      const response = await fetch(`${API}/Delete/${id}`, {
        method: 'DELETE'
      });
      if (!response.ok) throw new Error('Failed to delete record.');
      setMessage('Recycling drive deleted successfully!');
      await loadDrives();
    } catch (err) {
      setError(err.message || 'Unable to delete record.');
    }
  }

  return (
    <div className="app-shell">
      <nav className="navbar navbar-dark px-3 px-md-5">
        <a className="navbar-brand fw-bold" href="#">
          ♻ Recycle<span>Hub</span>
        </a>
        <span className="text-white-50 small">A cleaner tomorrow starts today</span>
      </nav>

      <header className="hero-section">
        <div className="container py-5">
          <span className="eyebrow">SUSTAINABILITY MANAGEMENT</span>
          <h1>Recycling Drive<br /><span>Dashboard</span></h1>
          <p>Manage collection drives, track recyclable materials, and make every kilogram count.</p>
          <div className="hero-stats">
            <div><strong>{drives.length}</strong><span>Total Drives</span></div>
            <div><strong>{drives.filter(d => d.completed === true).length}</strong><span>Completed</span></div>
            <div><strong>{drives.reduce((sum, d) => sum + (Number(d.collectedKg) || 0), 0).toLocaleString()}</strong><span>Kg Collected</span></div>
          </div>
        </div>
      </header>

      <main className="container py-4 py-md-5">
        {message && <div className="alert alert-success">{message}</div>}
        {error && <div className="alert alert-danger">{error}</div>}

        <div className="row g-4">
          <div className="col-lg-4">
            <section className="panel form-panel">
              <div className="section-kicker">NEW ENTRY</div>
              <h2>Add a Drive</h2>
              <p className="muted">Enter the details of a recycling collection.</p>

              <form onSubmit={addDrive}>
                <div className="mb-3">
                  <label className="form-label">Drive Location</label>
                  <input className="form-control" name="driveLocation"
                    placeholder="e.g. Vijayawada" value={form.driveLocation}
                    onChange={handleChange} maxLength="100" required />
                </div>

                <div className="mb-3">
                  <label className="form-label">Material Type</label>
                  <select className="form-select" name="materialType"
                    value={form.materialType} onChange={handleChange} required>
                    <option value="">Select material</option>
                    <option>Plastic</option>
                    <option>Paper</option>
                    <option>Metal</option>
                    <option>Glass</option>
                    <option>Electronic Waste</option>
                    <option>Mixed Recyclables</option>
                    <option>Other</option>
                  </select>
                </div>

                <div className="mb-3">
                  <label className="form-label">Collected (kg)</label>
                  <input className="form-control" type="number" min="0"
                    step="any" name="collectedKg" placeholder="e.g. 25.5"
                    value={form.collectedKg} onChange={handleChange} required />
                </div>

                <div className="mb-4">
                  <label className="form-label">Drive Status</label>
                  <select className="form-select" name="completed"
                    value={form.completed} onChange={handleChange}>
                    <option value="false">In Progress</option>
                    <option value="true">Completed</option>
                  </select>
                </div>

                <button className="btn btn-green w-100" disabled={saving}>
                  {saving ? 'Adding...' : '+ Add Recycling Drive'}
                </button>
              </form>
            </section>
          </div>

          <div className="col-lg-8">
            <section className="panel records-panel">
              <div className="d-flex justify-content-between align-items-start gap-3 mb-4">
                <div>
                  <div className="section-kicker">YOUR COLLECTIONS</div>
                  <h2>Recycling Records</h2>
                  <p className="muted mb-0">View and manage all recorded drives.</p>
                </div>
                <button className="btn btn-outline-success btn-sm"
                  onClick={loadDrives} disabled={loading}>
                  {loading ? 'Loading...' : '↻ Refresh'}
                </button>
              </div>

              {loading ? (
                <div className="empty-state">Loading recycling records...</div>
              ) : drives.length === 0 ? (
                <div className="empty-state">
                  <div className="empty-icon">♻</div>
                  <h3>No drives yet</h3>
                  <p>Add your first recycling drive using the form.</p>
                </div>
              ) : (
                <div className="table-responsive">
                  <table className="table align-middle">
                    <thead>
                      <tr>
                        <th>ID</th><th>Location / Material</th>
                        <th>Collected</th><th>Status</th><th>Action</th>
                      </tr>
                    </thead>
                    <tbody>
                      {drives.map(drive => (
                        <tr key={drive.id}>
                          <td className="id-cell">#{drive.id}</td>
                          <td>
                            <div className="fw-semibold">{drive.driveLocation}</div>
                            <small className="muted">{drive.materialType}</small>
                          </td>
                          <td className="fw-semibold">{drive.collectedKg} kg</td>
                          <td>
                            <span className={`status-pill ${drive.completed ? 'done' : 'pending'}`}>
                              {drive.completed ? 'Completed' : 'In Progress'}
                            </span>
                          </td>
                          <td>
                            <button className="btn btn-sm btn-delete"
                              onClick={() => deleteDrive(drive.id)}>
                              Delete
                            </button>
                          </td>
                        </tr>
                      ))}
                    </tbody>
                  </table>
                </div>
              )}
            </section>
          </div>
        </div>

        <footer className="text-center py-4">
          <span>♻ Small actions. Cleaner communities. Greener future.</span>
        </footer>
      </main>
    </div>
  );
}

export default App;
