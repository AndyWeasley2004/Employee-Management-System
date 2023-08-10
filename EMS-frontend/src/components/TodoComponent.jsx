import {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {addTodo, getTodo, updateTodo} from "../services/TodoService.js";

const TodoComponent = () => {
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [status, setStatus] = useState('');

    const {employeeId, todoId} = useParams();
    const navigator = useNavigate();

    useEffect( () => {

        if(todoId){
            getTodo(employeeId, todoId).then((response) => {
                console.log(response.data);
                setName(response.data.name);
                setDescription(response.data.description);
                setStatus(response.data.status);
            }).catch(error => {
                console.error(error);
            });
        }
    }, [todoId])

    const [errors, setErrors] = useState({
        name: '',
        description: '',
        status: '',
    })

    function saveOrUpdateTodo(e) {
        e.preventDefault();
        if(validateForm()){
            const todo = { name, description, status, employeeId: employeeId};
            console.log(todo);
            console.log("/" + employeeId + "/update/" + todoId)
            if(todoId){
                updateTodo(employeeId, todoId, todo).then((response) => {
                    console.log(response.data);
                    navigator(`/employee/${employeeId}/todos`);
                }).catch(error => {
                    console.error(error);
                });
            } else{
                addTodo(employeeId, todo).then((response) => {
                    console.log(response.data);
                    navigator(`/employee/${employeeId}/todos`);
                }).catch(error => {
                    console.error(error);
                });
            }
        }
    }

    function pageTitle(){
        if(todoId){
            return <h2 className="text-center">Update Todo</h2>
        } else {
            return <h2 className="text-center">Add Todo</h2>
        }
    }

    function validateForm(){
        let valid = true;

        const errorsCopy = {... errors}

        if(name.trim()){
            errorsCopy.name = '';
        }else{
            errorsCopy.name = 'Name is Required';
            valid = false;
        }

        if(description.trim()){
            errorsCopy.description = '';
        }else{
            errorsCopy.description = 'Description is Required';
            valid = false;
        }

        if(status.trim()){
            errorsCopy.status = '.';
        }else{
            errorsCopy.status = 'Status is Required';
            valid = false;
        }

        setErrors(errorsCopy);

        return valid;
    }


    return (
        <div className='container'>
            <br/><br/>
            <div className="row">
                <div className="card col-md-6 offset-md-3 offset-md-3">
                    {
                        pageTitle()
                    }
                    <div className="card-body">
                        <form>
                            <div className='form-group mb-2'>
                                <label className='form-label'>Todo Name:</label>
                                <input
                                    type='text'
                                    placeholder="Enter Todo Name"
                                    className= {`form-control ${errors.name ? 'is-invalid' : ''}`}
                                    name='name'
                                    value={name}
                                    onChange={(e) => setName(e.target.value)}
                                />
                                {errors.name && <div className='invalid-feedback'>{errors.name} </div> }
                            </div>

                            <div className='form-group mb-2'>
                                <label className='form-label'>Todo Description:</label>
                                <input
                                    type='text'
                                    placeholder="Enter Todo Description"
                                    className= {`form-control ${errors.description ? 'is-invalid' : ''}`}
                                    name='description'
                                    value={description}
                                    onChange={(e) => setDescription(e.target.value)}
                                />
                                {errors.description && <div className='invalid-feedback'>{errors.description} </div> }
                            </div>

                            <div className='form-group mb-2'>
                                <label className='form-label'>Todo Status:</label>
                                <select
                                    className= {`form-control ${errors.status ? 'is-invalid' : ''}`}
                                    value={status}
                                    onChange={(e) => setStatus(e.target.value)}
                                >
                                    <option value='.'>Select Status</option>
                                    <option value='1'>In Complete</option>
                                    <option value='2'>In Progress</option>
                                    <option value='3'>Complete</option>
                                </select>
                                {errors.status && <div className='invalid-feedback'>{errors.status} </div> }
                            </div>

                            <button className='btn btn-success' onClick={saveOrUpdateTodo}>Submit</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    )

}

export default TodoComponent