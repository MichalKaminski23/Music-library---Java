/**
 * This package contains the model classes that represent the data 
 * structures and business logic for the music application. 
 * It includes representations for songs and manages exceptions 
 * related to input validation, ensuring data integrity.
 * 
 * Key classes:
 * - {@link Model.Song}: Represents a song with attributes such as 
 *   title, composer, album, release date, and duration.
 * - {@link Model.SongList}: Manages a collection of songs and provides 
 *   methods for adding, removing, and updating songs in the list.
 * - {@link Model.InvalidDateFormatException}: Custom exception for handling 
 *   errors related to invalid date formats.
 * - {@link Model.InvalidIntException}: Custom exception for handling 
 *   errors related to invalid integer inputs.
 * - {@link Model.StringIsEmptyException}: Custom exception for handling 
 *   cases where string inputs are empty.
 * 
 * @author Michal Kaminski
 * @version 1.0
 */
package Model;
