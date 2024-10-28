@import com.khadija.taskmaster.dto.TaskDto
@import java.time.format.DateTimeFormatter
@param TaskDto task

<tr id="task-row-${task.id()}">
    <td class="py-2 px-4">${task.title()}</td>
    <td class="py-2 px-4">${task.description()}</td>
    <td class="py-2 px-4">${task.taskStatus()}</td>
    <td class="py-2 px-4">${task.createdDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}</td>
    <td class="py-2 px-4">${task.dueDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}</td>
    <td class="py-2 px-4">
        <button hx-get="/${task.id()}"
                hx-target="#task-row-${task.id()}"
                hx-swap="outerHTML"
                class="text-blue-500 hover:text-blue-700">Edit
        </button>
        <button hx-delete="/delete-task/${task.id()}"
                hx-target="closest tr"
                hx-swap="outerHTML swap:1s"
                class="text-red-500 hover:text-red-700 ml-2">Delete
        </button>
    </td>
</tr>
