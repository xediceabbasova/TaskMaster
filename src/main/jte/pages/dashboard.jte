@import java.util.List
@import com.khadija.taskmaster.dto.TaskDto

@param String username
@param List<TaskDto> tasks

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Dashboard</title>
    <script src="https://unpkg.com/htmx.org@1.7.0"></script>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto mt-10">
    <h1 class="text-xl font-bold">Welcome, ${username}</h1>

    <div class="my-5">
        <form hx-post="/add-task"
              hx-target="#tasks-table"
              hx-swap="beforeend" class="flex gap-2">
            <input type="text" name="title" placeholder="Task Title" class="border p-2 rounded w-1/4" required/>
            <input type="text" name="description" placeholder="Task Description" class="border p-2 rounded w-1/4"
                   required/>
            <input type="date" name="dueDate" class="border p-2 rounded w-1/4" required/>
            <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded">Add Task</button>
        </form>
    </div>

    <table class="min-w-full bg-white shadow-md rounded-lg">
        <thead class="bg-gray-50">
        <tr>
            <th class="py-2 px-4">Title</th>
            <th class="py-2 px-4">Description</th>
            <th class="py-2 px-4">Status</th>
            <th class="py-2 px-4">Created Date</th>
            <th class="py-2 px-4">Due Date</th>
            <th class="py-2 px-4">Actions</th>
        </tr>
        </thead>
        <tbody id="tasks-table">

        @for(TaskDto task : tasks)
            @template.pages.task-row(task = task)
        @endfor

        </tbody>
    </table>
</div>
</body>
</html>
