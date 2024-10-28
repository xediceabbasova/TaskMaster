@import com.khadija.taskmaster.dto.TaskDto
@import java.time.format.DateTimeFormatter
@param TaskDto task

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task Details</title>
    <script src="https://unpkg.com/htmx.org@1.7.0"></script>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">
<div class="container mx-auto mt-10">
    <h1 class="text-xl font-bold">Task Details</h1>

    <div class="mt-5">
        <p class="mb-2"><strong>Title:</strong> ${task.title()}</p>
        <p class="mb-2"><strong>Description:</strong> ${task.description()}</p>
        <p class="mb-2"><strong>Status:</strong> ${task.taskStatus()}</p>
        <p class="mb-2"><strong>Created
                Date:</strong> ${task.createdDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}</p>
        <p class="mb-2"><strong>Due Date:</strong> ${task.dueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}
        </p>
    </div>

    <form hx-post="/update/${task.id()}"
          hx-redirect="/dashboard"
          class="mt-8 bg-white p-5 rounded shadow">
        <div class="space-y-4">
            <div>
                <label class="block text-sm font-medium mb-1">Title</label>
                <input type="text"
                       name="title"
                       value="${task.title()}"
                       class="w-full border p-2 rounded"/>
            </div>

            <div>
                <label class="block text-sm font-medium mb-1">Description</label>
                <input type="text"
                       name="description"
                       value="${task.description()}"
                       class="w-full border p-2 rounded"/>
            </div>

            <div>
                <label class="block text-sm font-medium mb-1">Due Date</label>
                <input type="date"
                       name="dueDate"
                       value="${task.dueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}"
                       class="w-full border p-2 rounded"/>
            </div>

            <button type="submit"
                    class="w-full bg-blue-500 text-white px-4 py-2 rounded hover:bg-blue-600">
                Update Task
            </button>
        </div>
    </form>
</div>
</body>
</html>
